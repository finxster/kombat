package io.kombat.core.servlet;

import io.kombat.core.servlet.exceptions.HttpException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public class ServletRoutes {

    private Map<Class, ServletController> controllers = new LinkedHashMap<Class, ServletController>();
    private Map<String, Map<Pattern, ServletMapping>> routes = new LinkedHashMap<String, Map<Pattern, ServletMapping>>();

    private ServletController getController(Class className) {
        ServletController servletController = controllers.get(className);

        if (servletController == null) {
            servletController = new ServletController(className);
        }

        controllers.put(className, servletController);
        return servletController;
    }

    public void addMethod(Class className, Method method) {
        getController(className).addAction(method);
    }

    public void action(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, IOException {
        Matcher matcher;
        Map<Pattern, ServletMapping> mappings = this.routes.get(request.getMethod());

        if (mappings == null) {
            return;
        }
        try {
            for (Map.Entry<Pattern, ServletMapping> item : mappings.entrySet()) {
                matcher = item.getKey().matcher(request.getRequestURI());
                if (matcher.find()) {
                    item.getValue().call(request, response);
                    return;
                }
            }
        } catch (InvocationTargetException e) {

            if (!(e.getCause() instanceof HttpException)) {
                response.sendError(500, e.getCause().getMessage());
                return;
            }

            HttpException ex = (HttpException) e.getCause();

            if (ex.getRedirect() == null) {
                response.sendError(ex.getCode());
                return;
            }
            response.setStatus(ex.getCode());
            response.sendRedirect(ex.getRedirect());
            return;

        }

        response.sendError(404);
    }

    private void register(Class className, String method, String uriString, Method action) throws InstantiationException, IllegalAccessException {

        List<String> params = new ArrayList<String>();
        Matcher matcher = Pattern.compile("(/):([\\w]+)(\\(.*\\))?").matcher(uriString);

        while (matcher.find()) {
            if (matcher.groupCount() > 3) {
                uriString = uriString.replace(matcher.group(0), matcher.group(1) + matcher.group(3));
            } else {
                uriString = uriString.replace(matcher.group(0), matcher.group(1) + "([0-9A-Z]+)");
            }
            params.add(matcher.group(2));
        }

        Pattern uri = Pattern.compile("^" + uriString.replaceAll("/$", "/?") + "$");
        Map<Pattern, ServletMapping> maps = this.routes.get(method);
        if (maps == null) {

            maps = new LinkedHashMap<Pattern, ServletMapping>();
            maps.put(uri, new ServletMapping(getController(className), uri, params.toArray(new String[params.size()]), action));


        } else {

            ServletMapping mapping = maps.get(uri);
            if (mapping == null) {
                mapping = new ServletMapping(getController(className), uri, params.toArray(new String[params.size()]), action);
            } else {
                mapping.setAction(action);
            }

            maps.put(uri, mapping);
        }


        this.routes.put(method, maps);
    }

    public void GET(Class<?> controller, String uri, Method action) throws IllegalAccessException, InstantiationException {
        this.register(controller, HttpMethod.GET, uri, action);
    }

    public void POST(Class<?> controller, String uri, Method action) throws IllegalAccessException, InstantiationException {
        this.register(controller, HttpMethod.POST, uri, action);
    }

    public void PUT(Class<?> controller, String uri, Method action) throws IllegalAccessException, InstantiationException {
        this.register(controller, HttpMethod.PUT, uri, action);
    }

    public void DELETE(Class<?> controller, String uri, Method action) throws IllegalAccessException, InstantiationException {
        this.register(controller, HttpMethod.DELETE, uri, action);
    }

}

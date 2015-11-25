package io.kombat.core.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.reflections.Reflections;

import io.kombat.core.annotation.Controller;
import io.kombat.core.annotation.DELETE;
import io.kombat.core.annotation.GET;
import io.kombat.core.annotation.POST;
import io.kombat.core.annotation.PUT;

/**
 * Created by ac-bsilva on 18/11/15.
 */
public class DefaultServlet extends HttpServlet {

    private static final long serialVersionUID = 6250249837144780005L;

    private ServletRoutes routes = new ServletRoutes();

    private boolean methodAllowed(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        return parameterTypes.length == 2 && (parameterTypes[0] == HttpServletRequest.class && parameterTypes[1] == HttpServletResponse.class);
    }

    @Override
    public void init() {

        Reflections reflections = new Reflections("io.kombat.web");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);

        if (annotated.size() == 0) {
            return;
        }

        for (Class<?> controller : annotated) {
            Controller request = controller.getAnnotation(Controller.class);
            String prefix = request.value();
            try {
                for (Method method : controller.getDeclaredMethods()) {

                    routes.addMethod(controller, method);

                    GET getAnnotation = method.getAnnotation(GET.class);

                    if (getAnnotation != null && methodAllowed(method)) {
                        routes.GET(controller, String.format("%s/%s", prefix, getAnnotation.value().replaceFirst("/", "")), method);
                        continue;
                    }

                    POST postAnnotation = method.getAnnotation(POST.class);

                    if (postAnnotation != null && methodAllowed(method)) {
                        routes.POST(controller, String.format("%s/%s", prefix, postAnnotation.value().replaceFirst("/", "")), method);
                        continue;
                    }

                    PUT putAnnotation = method.getAnnotation(PUT.class);

                    if (putAnnotation != null && methodAllowed(method)) {
                        routes.PUT(controller, String.format("%s/%s", prefix, putAnnotation.value().replaceFirst("/", "")), method);
                        continue;
                    }

                    DELETE delAnnotation = method.getAnnotation(DELETE.class);

                    if (delAnnotation != null && methodAllowed(method)) {
                        routes.DELETE(controller, String.format("%s/%s", prefix, delAnnotation.value().replaceFirst("/", "")), method);
                    }

                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            routes.action(request, response);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doAction(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doAction(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doAction(request, response);
    }
}

package io.kombat.core.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.kombat.core.annotation.Before;

/**
 * Created by ac-bsilva on 19/11/15.
 */
public class ServletMapping {

    private final ServletController controller;
    private final Pattern uri;
    private final String[] params;
    private Method action;

    public ServletMapping(ServletController controller, Pattern uri, String[] params, Method action) throws IllegalAccessException, InstantiationException {
        this.controller = controller;
        this.uri = uri;
        this.params = params;
        this.action = action;
    }

    public Method getAction() {
        return action;
    }

    public void setAction(Method action) {
        this.action = action;
    }

    private void doCall(Method method, HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException {

        Before before = method.getAnnotation(Before.class);

        if (before != null) {

            for (String methodName : before.value()) {
                Method b = this.controller.getAction(methodName);

                if (b != null) {
                    doCall(b, request, response);
                }
            }
        }

        method.invoke(this.controller.getInstance(), request, response);

    }

    public void call(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, IOException, InvocationTargetException {

        String uri = request.getRequestURI();

        if (this.params.length > 0) {
            Matcher matcher = this.uri.matcher(uri);
            if (matcher.find()) {
                for (int i = 0, j = this.params.length; i < j; i++) {
                    if (matcher.groupCount() >= i) {
                        request.setAttribute(this.params[i], matcher.group(i + 1));
                    }
                }
            }
        }

        doCall(this.action, request, response);

    }
}
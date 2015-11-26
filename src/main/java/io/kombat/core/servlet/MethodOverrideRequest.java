package io.kombat.core.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public class MethodOverrideRequest extends HttpServletRequestWrapper {

    private static final String METHOD_OVERRIDE_PARAM = "_method";

    private final String method;

    public MethodOverrideRequest(HttpServletRequest request) throws IOException {
        super(request);
        String method = request.getParameter(METHOD_OVERRIDE_PARAM);
        if (method == null) {
            method = request.getMethod();
        }
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }

}

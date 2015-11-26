package io.kombat.core.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public class BodyParserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpRequest request = new HttpRequest((HttpServletRequest) servletRequest);
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

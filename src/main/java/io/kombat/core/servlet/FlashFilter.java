package io.kombat.core.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by ac-bsilva on 19/11/15.
 */
public class FlashFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getRequestURI().matches("^/assets.*")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = request.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        String name;
        while (attributeNames.hasMoreElements()) {
            name = attributeNames.nextElement();
            if (name.matches("^flash\\..*")) {
                Object attribute = session.getAttribute(name);
                session.removeAttribute(name);
                request.setAttribute(name.replace("flash.", ""), attribute);
            }
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

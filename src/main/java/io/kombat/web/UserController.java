package io.kombat.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import io.kombat.core.annotation.Before;
import io.kombat.core.annotation.Controller;
import io.kombat.core.annotation.DELETE;
import io.kombat.core.annotation.GET;
import io.kombat.core.annotation.POST;
import io.kombat.core.annotation.PUT;
import io.kombat.core.servlet.HttpRequest;
import io.kombat.core.servlet.exceptions.HttpException;
import io.kombat.domain.model.User;
import io.kombat.domain.services.UserService;

/**
 * Created by ac-bsilva on 13/11/15.
 */
@Controller("/users")
public class UserController {

    @Inject
    private Validator validator;

    @Inject
    private UserService service;

    public void current(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("current", service.one(Long.parseLong((String) request.getAttribute("id"))));
        } catch (SQLException e) {
            response.sendError(404);
        }
    }

    public void user(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpRequest req = (HttpRequest) request;
        User user = new User();

        Map<String, Object> params = req.getParams();
        user.setEmail((String) params.get("email"));
        user.setName((String) params.get("name"));
        user.setPassword((String) params.get("password"));

        HttpSession session = request.getSession();

        Set<ConstraintViolation<User>> validate = validator.validate(user);

        if (validate.size() > 0) {
            Map<String, List<String>> validations = new LinkedHashMap<String, List<String>>();
            for (ConstraintViolation<User> validation : validate) {

                String key = validation.getPropertyPath().toString();
                List<String> value = validations.get(key);

                if (value == null) {
                    value = new ArrayList<String>();
                }
                value.add(validation.getMessage());
                validations.put(key, value);

            }

            session.setAttribute("flash.validations", validations);
            session.setAttribute("flash.user", user);
            throw new HttpException(400, request.getHeader("referer"));
        }

        request.setAttribute("user", user);
    }

    @Before(value = {"current", "user"})
    @PUT("/:id")
    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User current = (User) request.getAttribute("current");
        User user = (User) request.getAttribute("user");
        String uri = String.format("/users/%d", current.getId());
        HttpSession session = request.getSession();

        try {

            current.setEmail(user.getEmail());
            current.setName(user.getName());
            current.setPassword(user.getPassword());
            service.save(current);

            session.setAttribute("flash.notice", "User saved with success.");
            response.sendRedirect(uri);

        } catch (SQLException e) {
            session.setAttribute("flash.user", user);
            session.setAttribute("flash.warning", e.getMessage());
            response.sendRedirect(uri);
        }

    }

    @DELETE("/:id")
    public void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = "/users";
        HttpSession session = request.getSession();
        try {
            service.destroy(Long.parseLong((String) request.getAttribute("id")));
            session.setAttribute("flash.notice", "User removed with success.");
            response.sendRedirect(uri);

        } catch (SQLException e) {
            session.setAttribute("flash.warning", e.getMessage());
            response.sendRedirect(uri);
        }
    }

    @Before("user")
    @POST
    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getAttribute("user");
        String uri = "/users/new";
        HttpSession session = request.getSession();

        try {
            service.create(user);
            session.setAttribute("flash.notice", "User created with success.");
            response.sendRedirect("/users");
        } catch (SQLException e) {
            session.setAttribute("flash.user", user);
            session.setAttribute("flash.warning", e.getMessage());
            response.sendRedirect(uri);
        }
    }

    @GET("/new")
    public void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/users/new.jsp").forward(request, response);
    }

    @GET
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("users", service.fetch(0, -1));
        } catch (SQLException e) {
            request.setAttribute("error", String.format("An Error ocurred %s", e.getMessage()));
        }

        request.getRequestDispatcher("/WEB-INF/views/users/index.jsp").forward(request, response);

    }

    @Before("current")
    @GET("/:id")
    public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/users/show.jsp").forward(request, response);
    }

}

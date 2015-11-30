package io.kombat.web;

import io.kombat.core.annotation.*;
import io.kombat.core.servlet.exceptions.HttpException;
import io.kombat.core.utils.FileManager;
import io.kombat.domain.model.GenericModel;
import io.kombat.domain.services.GenericService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public abstract class AbstractCRUDController<T extends GenericModel, S extends GenericService<T>> {

    @Inject
    protected Validator validator;

    @Inject
    protected S service;

    @Inject
    protected FileManager manager;

    public static final Integer DEFAULT_PAGE_SIZE = 20;

    protected String route;

    protected String new_url() {
        return route + "/new";
    }

    protected String model_url(Long id) {
        return String.format(route + "/%d", id);
    }

    protected String view_path(String file) {
        return String.format("/WEB-INF/views/%s/%s", route, file);
    }

    public AbstractCRUDController() {
        Controller annotation = this.getClass().getAnnotation(Controller.class);
        route = annotation.value();
    }

    public void current(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("current", service.one(Long.parseLong((String) request.getAttribute("id"))));
        } catch (SQLException e) {
            request.getSession().setAttribute("flash.error", e.getMessage());
            throw new HttpException(500, route);
        }
    }

    public void validate(T model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Set<ConstraintViolation<T>> validate = validator.validate(model);

        if (validate.size() > 0) {
            Map<String, List<String>> validations = new LinkedHashMap<String, List<String>>();
            for (ConstraintViolation<T> validation : validate) {

                String key = validation.getPropertyPath().toString();
                List<String> value = validations.get(key);

                if (value == null) {
                    value = new ArrayList<String>();
                }
                value.add(validation.getMessage());
                validations.put(key, value);

            }

            session.setAttribute("flash.validations", validations);
            session.setAttribute("flash.model", model);
            throw new HttpException(400, request.getHeader("referer"));
        }

        request.setAttribute("model", model);
    }

    @DELETE("/:id")
    public void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            service.destroy(Long.parseLong((String) request.getAttribute("id")));
            session.setAttribute("flash.notice", "Model removed with success.");
            response.sendRedirect(route);

        } catch (SQLException e) {
            session.setAttribute("flash.warning", e.getMessage());
            response.sendRedirect(route);
        }
    }

    public abstract void model(HttpServletRequest request, HttpServletResponse response) throws IOException;

    @Before(value = {"model"})
    @PUT("/:id")
    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong((String) request.getAttribute("id"));
        T model = (T) request.getAttribute("model");
        String uri = model_url(id);
        HttpSession session = request.getSession();

        try {
            model.setId(id);
            service.save(model);

            session.setAttribute("flash.notice", "Model saved with success.");
            response.sendRedirect(uri);

        } catch (SQLException e) {
            session.setAttribute("flash.model", model);
            session.setAttribute("flash.warning", e.getMessage());
            response.sendRedirect(uri);
        }

    }

    @Before("model")
    @POST
    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        T model = (T) request.getAttribute("model");
        HttpSession session = request.getSession();

        try {
            service.create(model);
            session.setAttribute("flash.notice", "Model created with success.");
            response.sendRedirect(route);
        } catch (SQLException e) {
            session.setAttribute("flash.model", model);
            session.setAttribute("flash.warning", e.getMessage());
            response.sendRedirect(new_url());
        }
    }

    @GET("/new")
    public void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(view_path("new.jsp")).forward(request, response);
    }

    @GET
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, String[]> params = request.getParameterMap();
            Integer max = DEFAULT_PAGE_SIZE;
            Integer offset = 0;

            String[] maxes = params.get("max");
            String[] pages = params.get("page");

            if (maxes != null && maxes.length > 0) {
                max = Integer.valueOf(maxes[0]);
            }

            if (pages != null && pages.length > 0) {
                offset = (Integer.valueOf(pages[0]) - 1) * max;
            }

            request.setAttribute("models", service.fetch(params, offset, max));
        } catch (SQLException e) {
            request.setAttribute("error", String.format("An Error ocurred %s", e.getMessage()));
        }

        request.getRequestDispatcher(view_path("index.jsp")).forward(request, response);

    }

    @Before("current")
    @GET("/:id")
    public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(view_path("show.jsp")).forward(request, response);
    }
}

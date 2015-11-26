package io.kombat.web;

import io.kombat.core.annotation.*;
import io.kombat.core.servlet.MethodOverrideRequest;
import io.kombat.core.servlet.exceptions.HttpException;
import io.kombat.core.utils.FileManager;
import io.kombat.domain.model.Game;
import io.kombat.domain.services.GameService;

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
@Controller("/games")
public class GameController {

    @Inject
    private Validator validator;

    @Inject
    private GameService service;

    @Inject
    private FileManager manager;

    public void current(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("current", service.one(Long.parseLong((String) request.getAttribute("id"))));
        } catch (SQLException e) {
            throw new HttpException(404);
        }
    }

    public void game(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MethodOverrideRequest req = (MethodOverrideRequest) request;
        Game game = new Game();

        String picturePath = manager.create(request, "picture", "/games");

        game.setName(request.getParameter("name"));
        game.setPicture(picturePath);

        HttpSession session = request.getSession();

        Set<ConstraintViolation<Game>> validate = validator.validate(game);

        if (validate.size() > 0) {
            Map<String, List<String>> validations = new LinkedHashMap<String, List<String>>();
            for (ConstraintViolation<Game> validation : validate) {

                String key = validation.getPropertyPath().toString();
                List<String> value = validations.get(key);

                if (value == null) {
                    value = new ArrayList<String>();
                }
                value.add(validation.getMessage());
                validations.put(key, value);

            }

            session.setAttribute("flash.validations", validations);
            session.setAttribute("flash.game", game);
            throw new HttpException(400, request.getHeader("referer"));
        }

        request.setAttribute("game", game);
    }

    @Before(value = {"current", "game"})
    @PUT("/:id")
    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Game current = (Game) request.getAttribute("current");
        Game game = (Game) request.getAttribute("game");
        String uri = String.format("/games/%d", current.getId());
        HttpSession session = request.getSession();

        try {

            current.setName(game.getName());
            current.setPicture(game.getPicture());
            service.save(current);

            session.setAttribute("flash.notice", "Game saved with success.");
            response.sendRedirect(uri);

        } catch (SQLException e) {
            session.setAttribute("flash.game", game);
            session.setAttribute("flash.warning", e.getMessage());
            response.sendRedirect(uri);
        }

    }

    @DELETE("/:id")
    public void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = "/games";
        HttpSession session = request.getSession();
        try {
            service.destroy(Long.parseLong((String) request.getAttribute("id")));
            session.setAttribute("flash.notice", "Game removed with success.");
            response.sendRedirect(uri);

        } catch (SQLException e) {
            session.setAttribute("flash.warning", e.getMessage());
            response.sendRedirect(uri);
        }
    }

    @Before("game")
    @POST
    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Game game = (Game) request.getAttribute("game");
        String uri = "/games/new";
        HttpSession session = request.getSession();

        try {
            service.create(game);
            session.setAttribute("flash.notice", "Game created with success.");
            response.sendRedirect("/games");
        } catch (SQLException e) {
            session.setAttribute("flash.game", game);
            session.setAttribute("flash.warning", e.getMessage());
            response.sendRedirect(uri);
        }
    }

    @GET("/new")
    public void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/games/new.jsp").forward(request, response);
    }

    @GET
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("games", service.fetch(0, -1));
        } catch (SQLException e) {
            request.setAttribute("error", String.format("An Error ocurred %s", e.getMessage()));
        }

        request.getRequestDispatcher("/WEB-INF/views/games/index.jsp").forward(request, response);

    }

    @Before("current")
    @GET("/:id")
    public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/games/show.jsp").forward(request, response);
    }
}

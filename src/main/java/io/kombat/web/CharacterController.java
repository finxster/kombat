package io.kombat.web;

import io.kombat.core.annotation.*;
import io.kombat.core.servlet.exceptions.HttpException;
import io.kombat.domain.model.Character;
import io.kombat.domain.model.Game;
import io.kombat.domain.services.CharacterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 27/11/15.
 */
@Controller("/games/:game_id/characters")
public class CharacterController extends AbstractCRUDController<Character, CharacterService> {

    private String route(Long id) {
        return String.format("/games/%d/characters", id);
    }

    protected String model_url(Long gameId, Long id) {
        return String.format(route(gameId) + "/%d", id);
    }

    protected String new_url(Long gameId) {
        return route(gameId) + "/new";
    }

    public void game(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Long game_id = Long.parseLong((String) request.getAttribute("game_id"));
            request.setAttribute("game", service.game(game_id));
        } catch (SQLException e) {
            throw new HttpException(404);
        }
    }

    @Override
    @Before("game")
    public void current(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Game game = (Game) request.getAttribute("game");
        try {
            Long id = Long.parseLong((String) request.getAttribute("id"));
            request.setAttribute("current", service.oneByGame(id, game.getId()));
        } catch (SQLException e) {
            request.getSession().setAttribute("flash.error", e.getMessage());
            throw new HttpException(500, route(game.getId()));
        }
    }

    @Before("game")
    public void model(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Character character = new Character();

        String picturePath = manager.create(request, "picture", "/characters");

        character.setName(request.getParameter("name"));
        character.setGame((Game) request.getAttribute("game"));
        if (picturePath != null) {
            character.setPicture(picturePath);
        }

        validate(character, request, response);
    }

    @Override
    @Before(value = {"game"})
    @GET("/new")
    public void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.form(request, response);
    }

    @Override
    @DELETE("/:id")
    public void destroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long gameId = Long.parseLong((String) request.getAttribute("game_id"));

        try {
            Long id = Long.parseLong((String) request.getAttribute("id"));
            service.destroyByGame(id, gameId);
            session.setAttribute("flash.notice", "Model removed with success.");

        } catch (SQLException e) {
            session.setAttribute("flash.warning", e.getMessage());
        }

        response.sendRedirect(route(gameId));
    }

    @Override
    @Before(value = {"model"})
    @PUT("/:id")
    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Game game = (Game) request.getAttribute("game");
        Long id = Long.parseLong((String) request.getAttribute("id"));
        Character model = (Character) request.getAttribute("model");
        HttpSession session = request.getSession();

        try {
            model.setId(id);
            service.save(model);
            session.setAttribute("flash.notice", "Model saved with success.");

        } catch (SQLException e) {
            session.setAttribute("flash.model", model);
            session.setAttribute("flash.warning", e.getMessage());
        }

        response.sendRedirect(model_url(game.getId(), id));
    }

    @Override
    @Before("model")
    @POST
    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Game game = (Game) request.getAttribute("game");
        Character model = (Character) request.getAttribute("model");
        HttpSession session = request.getSession();

        try {
            service.create(model);
            session.setAttribute("flash.notice", "Model created with success.");
            response.sendRedirect(route(game.getId()));
        } catch (SQLException e) {
            session.setAttribute("flash.model", model);
            session.setAttribute("flash.warning", e.getMessage());
            response.sendRedirect(new_url(game.getId()));
        }

    }

    @Override
    @Before("game")
    @GET
    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long gameId = Long.parseLong((String) request.getAttribute("game_id"));
            Map<String, String[]> params = new LinkedHashMap<String, String[]>();

            params.putAll(request.getParameterMap());

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

            params.put("game_id", new String[]{gameId.toString()});

            request.setAttribute("models", service.fetch(params, offset, max));
        } catch (SQLException e) {
            request.setAttribute("error", String.format("An Error ocurred %s", e.getMessage()));
        }

        request.getRequestDispatcher(view_path("index.jsp")).forward(request, response);
    }

}

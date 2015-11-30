package io.kombat.web;

import io.kombat.core.annotation.Before;
import io.kombat.core.annotation.Controller;
import io.kombat.core.annotation.GET;
import io.kombat.domain.model.Character;
import io.kombat.domain.model.Game;
import io.kombat.domain.services.CharacterService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 27/11/15.
 */
@Controller("/characters")
public class CharacterController extends AbstractCRUDController<Character, CharacterService> {

    public void model(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Character character = new Character();

        String picturePath = manager.create(request, "picture", route);

        character.setName(request.getParameter("name"));
        character.setGame(new Game(Long.parseLong((String) request.getParameter("game_id"))));
        if (picturePath != null) {
            character.setPicture(picturePath);
        }

        validate(character, request, response);
    }

    public void games(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Game> games = service.games();
        if (games == null) {
            request.getSession().setAttribute("flash.warning", "No game available.");
            response.sendRedirect(route);
            return;
        }
        request.setAttribute("games", games);
    }

    @Override
    @Before("games")
    @GET("/new")
    public void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(view_path("new.jsp")).forward(request, response);
    }

    @Override
    @Before(value = {"current", "games"})
    @GET("/:id")
    public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(view_path("show.jsp")).forward(request, response);
    }

}

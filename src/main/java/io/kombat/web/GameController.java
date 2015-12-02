package io.kombat.web;

import io.kombat.core.annotation.Controller;
import io.kombat.domain.model.Game;
import io.kombat.domain.services.GameService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
@Controller("/games")
public class GameController extends AbstractCRUDController<Game, GameService> {

    public void model(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Game game = new Game();

        String picturePath = request.getParameter("picture");
        if (picturePath == null || picturePath.trim().equals("")) {
            picturePath = manager.create(request, "_picture", route);
        }

        game.setName(request.getParameter("name"));
        if (picturePath != null) {
            game.setPicture(picturePath);
        }

        validate(game, request, response);
    }
}

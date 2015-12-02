package io.kombat.web;

import io.kombat.core.annotation.Controller;
import io.kombat.domain.model.GamePlay;
import io.kombat.domain.model.Match;
import io.kombat.domain.model.MatchStatus;
import io.kombat.domain.services.MatchService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;

/**
 * Created by Vinicius Boson Kairala<viniciusboson@gmail.com> on 01/12/15.
 */
@Controller("/matches")
public class MatchController extends AbstractCRUDController<Match, MatchService> {

    public void model(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Match match = new Match();

        match.setGamePlay(new GamePlay(parseLong(request.getParameter("name"))));
        match.setStatus(MatchStatus.get(request.getParameter("name")));
        match.setExperience(parseLong(request.getParameter("experience")));

        validate(match, request, response);
    }
}
package io.kombat.domain.services;

import io.kombat.domain.dao.GameDAO;
import io.kombat.domain.model.Game;

import java.sql.SQLException;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public class GameServiceImpl extends AbstractGenericService<Game, GameDAO> implements GameService {

    private static final long serialVersionUID = 5996496213296314758L;

    @Override
    public Game oneBySlug(String slug) throws SQLException {
        return dao.oneBySlug(slug);
    }

}

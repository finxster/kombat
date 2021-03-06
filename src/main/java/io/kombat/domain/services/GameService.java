package io.kombat.domain.services;

import io.kombat.domain.model.Game;

import java.sql.SQLException;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public interface GameService extends GenericService<Game> {

    Game oneBySlug(String slug) throws SQLException;

}

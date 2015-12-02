package io.kombat.domain.dao;

import io.kombat.domain.model.Game;

import java.sql.SQLException;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public interface GameDAO extends GenericDAO<Game> {

    Game oneBySlug(String slug) throws SQLException;
}

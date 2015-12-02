package io.kombat.domain.dao;

import io.kombat.domain.model.Character;

import java.sql.SQLException;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 27/11/15.
 */
public interface CharacterDAO extends GenericDAO<Character> {

    Character oneByGame(Long id, Long gameId) throws SQLException;

    void destroyByGame(Long id, Long gameId) throws SQLException;

    void destroyByGame(Long id) throws SQLException;

}

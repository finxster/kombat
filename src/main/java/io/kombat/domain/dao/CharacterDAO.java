package io.kombat.domain.dao;

import io.kombat.domain.model.Character;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 27/11/15.
 */
public interface CharacterDAO extends GenericDAO<Character> {

    List<Character> byGame(Long id) throws SQLException;

    void destroyByGame(Long id) throws SQLException;

}

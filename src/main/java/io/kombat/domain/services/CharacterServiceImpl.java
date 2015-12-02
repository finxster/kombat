package io.kombat.domain.services;

import io.kombat.domain.dao.CharacterDAO;
import io.kombat.domain.model.Character;
import io.kombat.domain.model.Game;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 27/11/15.
 */
public class CharacterServiceImpl extends AbstractGenericService<Character, CharacterDAO> implements CharacterService {

    private static final long serialVersionUID = -6040404331501198101L;

    @Inject
    private GameService gameService;

    @Override
    public List<Game> games() {
        try {
            return gameService.fetch(null, 0, -1);
        } catch (SQLException e) {
            return null;
        }
    }

    public Game game(Long id) throws SQLException {
        return gameService.one(id);
    }

    @Override
    public Character oneByGame(Long id, Long gameId) throws SQLException {
        return dao.oneByGame(id, gameId);
    }

    @Override
    public void destroyByGame(Long id, Long gameId) throws SQLException {
        dao.destroyByGame(id, gameId);
    }
}

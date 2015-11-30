package io.kombat.domain.services;

import io.kombat.domain.model.Character;
import io.kombat.domain.model.Game;

import java.util.List;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 27/11/15.
 */
public interface CharacterService extends GenericService<Character> {

    List<Game> games();
}

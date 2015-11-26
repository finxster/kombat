package io.kombat.domain.model;

import junit.framework.TestCase;

/**
 * Created by ac-bsilva on 26/11/15.
 */
public class GameTest extends TestCase {


    public void testGetSlug() throws Exception {
        System.out.println("Assert slug is being populated correctly");
        Game game = new Game();
        game.setName("Mortal Kombat");
        assertEquals(game.getSlug(), "mortal-kombat");
    }
}
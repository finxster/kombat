package io.kombat.domain.model;

import java.util.Arrays;
import java.util.Collection;

import static java.lang.String.format;

/**
 * Created by Vinicius Boson Kairala<viniciusboson@gmail.com> on 30/11/15.
 */
public enum GamePlayType {
    TOURNAMENT("T", "Tournament"), SINGLE_MATCH("S", "Single Match");

    private final String code;

    private final String description;

    GamePlayType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static GamePlayType get(String code) {
        for (GamePlayType gamePlayType : all()) {
            if (gamePlayType.code.equals(code)) {
                return gamePlayType;
            }
        }
        throw new IllegalArgumentException(format("Game play type '%s' not found", code));
    }

    public static Collection<GamePlayType> all() {
        return Arrays.asList(TOURNAMENT, SINGLE_MATCH);
    }
}

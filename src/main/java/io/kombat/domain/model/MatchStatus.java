package io.kombat.domain.model;

import java.util.Arrays;
import java.util.Collection;

import static java.lang.String.format;

/**
 * Created by Vinicius Boson Kairala<viniciusboson@gmail.com> on 01/12/15.
 */
public enum MatchStatus {
    STARTED("S", "Started"), FINISHED("F", "Finished"), CANCELED("C", "Canceled");

    private String code;

    private String description;

    MatchStatus(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static MatchStatus get(final String code) {
        for(MatchStatus gamePlayType : all()) {
            if(gamePlayType.code.equals(code)) {
                return gamePlayType;
            }
        }
        throw new IllegalArgumentException(format("Match status '%s' not found", code));
    }

    public static Collection<MatchStatus> all() {
        return Arrays.asList(STARTED, FINISHED, CANCELED);
    }
}

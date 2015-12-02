package io.kombat.domain.model;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by  Vinicius Boson Kairala<viniciusboson@gmail.com> on 01/12/15.
 */
public class Match  implements GenericModel {

    private static final long serialVersionUID = -4777884510506641792L;

    private Long id;

    @NotNull
    private GamePlay gamePlay;

    private MatchStatus status;

    @NotNull
    private Long experience;

    private Timestamp created;

    private Timestamp updated;

    public Match() {
    }

    public Match(Long id, GamePlay gamePlay, MatchStatus status, Long experience,  Timestamp created, Timestamp updated) {
        this.id = id;
        this.status = status;
        this.experience = experience;
        this.gamePlay = gamePlay;
        this.created = created;
        this.updated = updated;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public GamePlay getGamePlay() {
        return gamePlay;
    }

    public void setGamePlay(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }
}

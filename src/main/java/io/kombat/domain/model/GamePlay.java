package io.kombat.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Vinicius Boson Kairala<viniciusboson@gmail.com> on 11/30/15.
 */
public class GamePlay implements GenericModel {

    private static final long serialVersionUID = -4777884510506641792L;

    private Long id;

    @NotEmpty
    private String description;

    @NotNull
    private GamePlayType type;

    private Timestamp created;

    private Timestamp updated;

    public GamePlay() {
    }

    public GamePlay(Long id, String description, GamePlayType type, Timestamp created,
                    Timestamp updated) {
        this.id = id;
        this.description = description;
        this.type = type;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public GamePlayType getType() {
        return type;
    }

    public void setType(GamePlayType type) {
        this.type = type;
    }
}

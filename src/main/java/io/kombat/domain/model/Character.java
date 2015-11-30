package io.kombat.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 27/11/15.
 */
public class Character implements GenericModel {

    private static final long serialVersionUID = -4777884510506641792L;

    private Long id;

    @NotEmpty
    private String name;

    private String picture;

    @NotNull
    private Game game;

    private Timestamp created;

    private Timestamp updated;

    public Character() {
    }

    public Character(Long id, String name, Game game, String picture, Timestamp created, Timestamp updated) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.game = game;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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
}

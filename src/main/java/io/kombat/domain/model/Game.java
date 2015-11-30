package io.kombat.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.List;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public class Game implements GenericModel {

    private static final long serialVersionUID = 5548046233660569710L;

    private Long id;

    @NotEmpty
    private String name;

    private String slug;

    private String picture;

    private Timestamp created;

    private Timestamp updated;

    private List<Character> characters;

    public Game() {
    }

    public Game(Long id) {
        this.id = id;
    }

    public Game(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Game(Long id, String name, String slug, String picture, Timestamp created, Timestamp updated) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.picture = picture;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (this.slug == null) {
            this.slug = Normalizer
                    .normalize(name, Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "")
                    .replaceAll("\\s", "-")
                    .toLowerCase();
        }
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}

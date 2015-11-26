package io.kombat.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.text.Normalizer;
import java.util.Calendar;

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

    private Calendar created;

    private Calendar updated;

    public Game() {
    }

    public Game(Long id, String name, String slug, String picture, Calendar created, Calendar updated) {
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

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }
}

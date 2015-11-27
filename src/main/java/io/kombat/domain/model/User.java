package io.kombat.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

/**
 * Created by ac-bsilva on 13/11/15.
 */
public class User implements GenericModel {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    private Long experience;

    private String picture;

    private Timestamp created;

    private Timestamp updated;

    public User() {

    }

    public User(Long id, String name, String email, Long experience, String picture, Timestamp created, Timestamp updated) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.experience = experience;
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
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
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

}

package io.kombat.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;


/**
 * Created by ac-bsilva on 13/11/15.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -2281033197628568888L;

    private Long id;

    @NotEmpty
    private String slug;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public User() {
    }

    public User(Long id, String slug, String name, String email, String password) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
        if (this.slug == null) {
            String[] split = name.split(" ");
            if (split.length > 1) {
                this.slug = split[0].toLowerCase().charAt(0) + split[split.length - 1].toLowerCase();
            } else {
                this.slug = split[0].toLowerCase();
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

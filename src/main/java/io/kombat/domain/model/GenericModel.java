package io.kombat.domain.model;

import java.io.Serializable;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public interface GenericModel extends Serializable {

    Long getId();

    void setId(Long id);
}

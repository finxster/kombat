package io.kombat.domain.services;

import io.kombat.domain.model.GenericModel;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public interface GenericService<T extends GenericModel> extends Serializable {

    T one(Long id) throws SQLException;

    void save(T model) throws SQLException;

    void create(T model) throws SQLException;

    void destroy(Long id) throws SQLException;

    List<T> fetch(Map<String, String[]> params, Integer offset, Integer limit) throws SQLException;
}

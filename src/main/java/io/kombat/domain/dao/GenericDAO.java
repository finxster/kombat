package io.kombat.domain.dao;

import io.kombat.domain.model.GenericModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public interface GenericDAO<T extends GenericModel> {

    T one(Long id) throws SQLException;

    void save(T model) throws SQLException;

    void create(T model) throws SQLException;

    void destroy(Long id) throws SQLException;

    List<T> fetch(Integer offset, Integer limit) throws SQLException;
}

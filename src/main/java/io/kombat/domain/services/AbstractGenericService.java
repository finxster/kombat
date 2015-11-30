package io.kombat.domain.services;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */

import io.kombat.domain.dao.GenericDAO;
import io.kombat.domain.model.GenericModel;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract class AbstractGenericService<T extends GenericModel, U extends GenericDAO<T>> implements GenericService<T> {

    private static final long serialVersionUID = -5422985347241623071L;

    @Inject
    private U dao;

    public T one(Long id) throws SQLException {
        return dao.one(id);
    }

    public void save(T model) throws SQLException {
        dao.save(model);
    }

    public void create(T model) throws SQLException {
        dao.create(model);

    }

    public void destroy(Long id) throws SQLException {
        dao.destroy(id);
    }

    public List<T> fetch(Map<String, String[]> params, Integer offset, Integer limit) throws SQLException {
        return dao.fetch(params, offset, limit);
    }
}

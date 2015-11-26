package io.kombat.domain.dao;

import java.sql.SQLException;
import java.util.List;

import io.kombat.domain.model.User;

/**
 * Created by ac-bsilva on 13/11/15.
 */
public interface UserDAO extends GenericDAO<User> {

    User one(Long id) throws SQLException;

    void save(User user) throws SQLException;

    void create(User user) throws SQLException;

    void destroy(Long id) throws SQLException;

    List<User> fetch(Integer offset, Integer limit) throws SQLException;
}

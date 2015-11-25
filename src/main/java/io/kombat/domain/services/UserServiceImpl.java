package io.kombat.domain.services;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import io.kombat.domain.dao.UserDAO;
import io.kombat.domain.model.User;

/**
 * Created by ac-bsilva on 25/11/15.
 */
public class UserServiceImpl implements UserService {
    private static final long serialVersionUID = 1543045928522017790L;

    @Inject
    private UserDAO userDAO;

    @Override
    public User one(Long id) throws SQLException {
        return userDAO.one(id);
    }

    @Override
    public void save(User user) throws SQLException {
        userDAO.save(user);
    }

    @Override
    public void create(User user) throws SQLException {
        userDAO.create(user);
    }

    @Override
    public void destroy(Long id) throws SQLException {
        userDAO.destroy(id);
    }

    @Override
    public List<User> fetch(Integer offset, Integer limit) throws SQLException {
        return userDAO.fetch(offset, limit);
    }
}

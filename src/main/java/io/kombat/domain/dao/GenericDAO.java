package io.kombat.domain.dao;

import io.kombat.domain.model.GenericModel;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Bruno de Queiroz<creativelikeadog@gmail.com> on 26/11/15.
 */
public interface GenericDAO<T extends GenericModel> extends Serializable {

    String HOST = System.getenv("ORACLE_PORT_1521_TCP_ADDR");

    String PORT = System.getenv("ORACLE_PORT_1521_TCP_PORT");

    String USER = "SYSTEM";

    String PASSWORD = "oracle";

    T one(Long id) throws SQLException;

    void save(T model) throws SQLException;

    void create(T model) throws SQLException;

    void destroy(Long id) throws SQLException;

    List<T> fetch(Integer offset, Integer limit) throws SQLException;
}

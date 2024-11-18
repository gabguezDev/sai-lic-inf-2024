package org.gabriel_dominguez.sgi.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
  Integer save(T obj) throws SQLException;

  T findById(Integer id) throws SQLException;

  List<T> findAll() throws SQLException;

  Integer modify(T obj) throws SQLException;

  Integer remove(Integer id);

}

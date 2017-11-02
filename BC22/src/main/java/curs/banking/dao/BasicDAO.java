package curs.banking.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface BasicDAO<T> {
  T findById(long pId);
  Collection<T> findAll();
  T insert(T pEntity);
  T update(T pEntity);
  void delete(T pEntity);
  
  <T> T loadFromResultSet(ResultSet pRS) throws SQLException; 
}

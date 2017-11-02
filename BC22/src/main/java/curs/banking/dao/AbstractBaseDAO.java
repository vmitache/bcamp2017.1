package curs.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import curs.banking.model.Account;
import curs.banking.model.Customer;

public abstract class AbstractBaseDAO<T> implements BasicDAO<T> {
  protected Connection mConnection;
  
  protected abstract T loadFromResultSet1(ResultSet pRS) throws SQLException; 

  @Override
  public Collection<T> findAll() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<T> result = new ArrayList<>();
    try {
      stmt = mConnection
          .prepareStatement(getSQLForFindAll());
      rs = stmt.executeQuery();
      while (rs.next()) {
        result.add(loadFromResultSet(rs));
      }
      return result;
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);

    }

  }
  
  @Override
  public T findById(long pId) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement(getSQLForFindAll() + " WHERE ID=?");
      stmt.setLong(1, pId);
      rs = stmt.executeQuery();
      if (rs.next()) {

        return loadFromResultSet(rs);
      } else {
        // throw new DAOException("Id not found:" + pId);
        return null;
      }
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);
    }
  }
  
  protected abstract String getSQLForFindAll();
}

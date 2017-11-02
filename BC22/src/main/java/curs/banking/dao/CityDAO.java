package curs.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import curs.banking.model.City;

public class CityDAO implements BasicDAO<City> {
  protected Connection mConnection;
  private CountryDAO mCountryDAO;

  public CityDAO(Connection pConnection) {
    mConnection = pConnection;
    mCountryDAO = new CountryDAO(pConnection);
  }

  @Override
  public City loadFromResultSet(ResultSet pRS) throws SQLException {
    City c = new City();
    long id = pRS.getLong(1);
    String name = pRS.getString(2);
    long countryId = pRS.getLong(3);
    if (!pRS.wasNull()) {
      c.setCountry(mCountryDAO.findById(countryId));
    }
    c.setId(id);
    c.setName(name);
    return c;
  }

  @Override
  public City findById(long pId) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("SELECT ID,NAME,COUNTRY_ID FROM BANK.CITY WHERE ID=?");
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

  @Override
  public Collection<City> findAll() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<City> result = new ArrayList<>();
    try {
      stmt = mConnection.prepareStatement("SELECT ID,NAME,COUNTRY_ID FROM BANK.CITY");
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

  /**
   * pEntity has an existing Country record
   */
  @Override
  public City insert(City pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("INSERT INTO BANK.CITY(NAME,COUNTRY_ID) VALUES(?,?)",
          Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, pEntity.getName());
      stmt.setLong(2, pEntity.getCountry().getId());
      stmt.executeUpdate();
      rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        long id = rs.getLong(1);
        return findById(id);
      } else {
        throw new DAOException("Id not found in insert");
      }
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);

    }
  }

  @Override
  public City update(City pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("UPDATE BANK.CITY SET NAME=?,COUNTRY_ID=? WHERE ID=?");
      stmt.setString(1, pEntity.getName());
      stmt.setLong(2, pEntity.getCountry().getId());
      stmt.setLong(3, pEntity.getId());
      stmt.executeUpdate();
      return findById(pEntity.getId());
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);

    }

  }

  @Override
  public void delete(City pEntity) {
    throw new DAOException("Not implemented!!!");

  }

}

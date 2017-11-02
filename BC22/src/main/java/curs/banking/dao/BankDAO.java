package curs.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import curs.banking.model.Bank;
import curs.banking.model.City;

public class BankDAO implements BasicDAO<Bank> {
  protected Connection mConnection;
  private AddressDAO mAddressDAO;

  public BankDAO(Connection pConnection) {
    mConnection = pConnection;
    mAddressDAO = new AddressDAO(pConnection);
  }
  
  @Override
  public Bank loadFromResultSet(ResultSet pRS) throws SQLException {
    Bank bank = new Bank();
    long id = pRS.getLong(1);
    String name = pRS.getString(2);
    long addressId = pRS.getLong(3);
    if (!pRS.wasNull()) {
      bank.setAdress(mAddressDAO.findById(addressId));
    }
    String fc = pRS.getString(4);

    bank.setId(id);
    bank.setName(name);
    bank.setFiscalCode(fc);
    return bank;
  }


  @Override
  public Bank findById(long pId) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("SELECT ID,NAME,ADDRESS_ID,FISCAL_CODE FROM BANK.BANK WHERE ID=?");
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
  public Collection<Bank> findAll() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Bank> result = new ArrayList<>();
    try {
      stmt = mConnection.prepareStatement("SELECT ID,NAME,ADDRESS_ID,FISCAL_CODE FROM BANK.BANK");
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
  public Bank insert(Bank pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("INSERT INTO BANK.BANK(NAME,ADDRESS_ID,FISCAL_CODE) VALUES(?,?,?)",
          Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, pEntity.getName());
      stmt.setLong(2, pEntity.getAdress().getId());
      stmt.setString(3, pEntity.getFiscalCode());
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
  public Bank update(Bank pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("UPDATE BANK.BANK SET NAME=?,ADRESS_ID=?,FISCAL_CODE=? WHERE ID=?");
      stmt.setString(1, pEntity.getName());
      stmt.setLong(2, pEntity.getAdress().getId());
      stmt.setString(3, pEntity.getFiscalCode());
      stmt.setLong(4, pEntity.getId());
      stmt.executeUpdate();
      return findById(pEntity.getId());
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);

    }
  }

  @Override
  public void delete(Bank pEntity) {
    throw new DAOException("Not implemented!!!");

  }

 
}

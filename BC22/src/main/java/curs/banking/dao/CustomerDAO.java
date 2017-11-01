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
import curs.banking.model.Customer;
import curs.banking.model.SexEnum;

public class CustomerDAO implements BasicDAO<Customer> {
  protected Connection mConnection;
  private AddressDAO mAddressDAO;

  public CustomerDAO(Connection pConnection) {
    mConnection = pConnection;
    mAddressDAO = new AddressDAO(pConnection);
  }

  @Override
  public Customer findById(long pId) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("SELECT ID,NAME,SSN,ADDRESS_ID,AGE,SEX FROM BANK.CUSTOMER WHERE ID=?");
      stmt.setLong(1, pId);
      rs = stmt.executeQuery();
      if (rs.next()) {
        Customer cust = new Customer();
        long id = rs.getLong(1);
        String name = rs.getString(2);
        String ssn = rs.getString(3);
        long addressId = rs.getLong(4);
        if (!rs.wasNull()) {
          cust.setAddress(mAddressDAO.findById(addressId));
        }
        int age = rs.getInt(5);
        String sex = rs.getString(6);

        cust.setId(id);
        cust.setName(name);
        cust.setSSN(ssn);
        cust.setVarsta(age);
        cust.setSex("M".equals(sex) ? SexEnum.M : SexEnum.F);
        return cust;
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
  public Collection<Customer> findAll() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Customer> result = new ArrayList<>();
    try {
      stmt = mConnection.prepareStatement("SELECT ID,NAME,SSN,ADDRESS_ID,AGE,SEX FROM BANK.CUSTOMER");
      rs = stmt.executeQuery();
      while (rs.next()) {
        Customer cust = new Customer();
        long id = rs.getLong(1);
        String name = rs.getString(2);
        String ssn = rs.getString(3);
        long addressId = rs.getLong(4);
        if (!rs.wasNull()) {
          cust.setAddress(mAddressDAO.findById(addressId));
        }
        int age = rs.getInt(5);
        String sex = rs.getString(6);

        cust.setId(id);
        cust.setName(name);
        cust.setSSN(ssn);
        cust.setVarsta(age);
        cust.setSex("M".equals(sex) ? SexEnum.M : SexEnum.F);
        result.add(cust);
      }
      return result;
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);

    }

  }

  @Override
  public Customer insert(Customer pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("INSERT INTO BANK.CUSTOMER(NAME,SSN,ADDRESS_ID,AGE,SEX) VALUES(?,?,?,?,?)",
          Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, pEntity.getName());
      stmt.setString(2, pEntity.getSSN());
      stmt.setLong(3, pEntity.getAddress().getId());
      stmt.setInt(4, pEntity.getVarsta());
      stmt.setString(5, pEntity.getSex().name());
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
  public Customer update(Customer pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("UPDATE BANK.CUSTOMER SET NAME=?,SSN=?,ADDRESS_ID=?,AGE=?,SEX=? WHERE ID=?");
      stmt.setString(1, pEntity.getName());
      stmt.setString(2, pEntity.getSSN());
      stmt.setLong(3, pEntity.getAddress().getId());
      stmt.setInt(4, pEntity.getVarsta());
      stmt.setString(5, pEntity.getSex().name());
      stmt.setLong(6, pEntity.getId());
      stmt.executeUpdate();
      return findById(pEntity.getId());
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);

    }

  }

  @Override
  public void delete(Customer pEntity) {
    throw new DAOException("Not implemented!!!");

  }

}

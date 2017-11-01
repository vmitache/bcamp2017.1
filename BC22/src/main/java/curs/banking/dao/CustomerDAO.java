package curs.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import curs.banking.model.Bank;
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
      stmt = mConnection.prepareStatement("SELECT ID,NAME,SSN,ADDRESS_ID,AGE,SEX FROM BANK.BANK WHERE ID=?");
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Customer insert(Customer pEntity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Customer update(Customer pEntity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(Customer pEntity) {
    // TODO Auto-generated method stub
    
  }

}

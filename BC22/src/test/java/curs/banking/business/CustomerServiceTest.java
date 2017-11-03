package curs.banking.business;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import curs.banking.dao.CityDAO;
import curs.banking.dao.DAOException;
import curs.banking.model.Address;
import curs.banking.model.City;
import curs.banking.model.Customer;
import curs.banking.model.SexEnum;

public class CustomerServiceTest {
  static final String DB_URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE";

 @Before
  public void setUp() throws Exception {
    Connection mConn = DriverManager.getConnection(DB_URL, "SA", "");
    Statement st = mConn.createStatement();
    StringBuilder sb = new StringBuilder();
    try {
      InputStream is = this.getClass().getResourceAsStream("/banking.sql");
      BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      br.lines().forEach(l -> sb.append(l).append("\n"));
      br.close();
      st.executeUpdate(sb.toString());
      st.close();
      mConn.close();
    } catch (Throwable th) {
      th.printStackTrace();
    }
  }

  @Test
  public void testTransferMoneySuccessfull() throws Exception {
    CustomerService cs = new CustomerService();
    assertTrue(cs.transferMoney(3, 1, 100));
    assertTrue(cs.transferMoney(1, 3, 200));

  }
  
  @Test
  public void testTransferMoneyUnsuccessfullWithInssuficientFund() throws Exception {
    CustomerService cs = new CustomerService();
    assertFalse(cs.transferMoney(3, 1, 1000));
  }
  
  @Test(expected=DAOException.class)
  public void testTransferMoneyUnsuccessfullWithDiferrentCurrency() throws Exception {
    CustomerService cs = new CustomerService();
    assertFalse(cs.transferMoney(1, 4, 5));
  }

  @Test
  public void testCreateCustomer() throws Exception {
    CustomerService cs = new CustomerService();
    Connection c = cs.getConnection();
    City city = new CityDAO(c).findById(1);
    c.close();
    ///
    Address addr = new Address();
    addr.setCity(city);
    addr.setStreet("Pafnutie street");
    addr.setNumber("66666");
    addr.setPostalCode("PC");
    Customer customer = new Customer();
    customer.setAddress(addr);
    customer.setName("Pafnutie gicu");
    customer.setSex(SexEnum.M);
    customer.setSSN("xxxxxxxxxxxx");
    customer.setVarsta(44);
    cs.createCustomer(customer, true);
  }


}

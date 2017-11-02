package curs.banking.dao;


import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import curs.banking.dao.AddressDAO;
import curs.banking.dao.CustomerDAO;
import curs.banking.model.Address;
import curs.banking.model.Customer;
import curs.banking.model.SexEnum;

public class CustomerDAOTest {
  static final String DB_URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE";

  Connection mConn;

  @Before
  public void setUp() throws Exception {
    mConn = DriverManager.getConnection(DB_URL, "SA", "");
    Statement st = mConn.createStatement();
    StringBuilder sb = new StringBuilder();
    try {
      InputStream is = this.getClass().getResourceAsStream("/banking.sql");
      BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      br.lines().forEach(l -> sb.append(l).append("\n"));
      br.close();
      st.executeUpdate(sb.toString());
      st.close();
    } catch (Throwable th) {
      th.printStackTrace();
    }
  }

  @Test
  public void testFindById() {
    assertEquals("Popescu", new CustomerDAO(mConn).findById(1).getName());
  }

  @Test
  public void testFindAll() {
    assertTrue(new CustomerDAO(mConn).findAll().size() > 0);
  }
  
  @Test
  public void testFindByName() {
    assertTrue(new CustomerDAO(mConn).findByName("Ionesc%").size() == 1);
  }

  @Test
  public void testInsert() {
    Address address = new AddressDAO(mConn).findById(1);
    Customer cust = new Customer();
    cust.setName("Pafnutie");
    cust.setSSN("1111111111");
    cust.setAddress(address);
    cust.setVarsta(22);
    cust.setSex(SexEnum.M);
    cust = new CustomerDAO(mConn).insert(cust);
    assertTrue(cust.equals(new CustomerDAO(mConn).findById(cust.getId())));
  }

  @Test
  public void testUpdate() {
    
  }

  @Test
  public void testDelete() {
  }



}

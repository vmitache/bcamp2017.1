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
import curs.banking.dao.BankDAO;
import curs.banking.model.Bank;

public class BankDAOTest {
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
    BankDAO bankDAO = new BankDAO(mConn);
    Bank bank = bankDAO.findById(1);
    assertNotNull(bank);
    assertEquals("BCR", bank.getName());
    assertNull(bank.getFiscalCode());
    
    bank = bankDAO.findById(314);
    assertNull(bank);
  }

  @Test
  public void testFindAll() {
    assertTrue(new BankDAO(mConn).findAll().size() > 0);
  }

  @Test
  public void testInsert() {
    BankDAO bankDAO = new BankDAO(mConn);
    Bank bank = new Bank();
    bank.setAdress(new AddressDAO(mConn).findById(1));
    bank.setFiscalCode("xxxx");
    bank.setName("Patria bank");
    bank = bankDAO.insert(bank);
    assertEquals("Patria bank", bankDAO.findById(bank.getId()).getName());;
  }

  @Test
  public void testUpdate() {
  }

  @Test
  public void testDelete() {
  }

}

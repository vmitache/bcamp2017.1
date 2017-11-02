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

import curs.banking.model.Account;
import curs.banking.model.AccountType;
import curs.banking.model.Bank;
import curs.banking.model.Currency;
import curs.banking.model.Customer;

public class AccountDAOTest {
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
  public void testFindById() throws Exception {
    assertTrue("RNCB0101010101101".equals(new AccountDAO(mConn).findById(1).getIBAN()));
  }

  @Test
  public void testFindAll() throws Exception {
    assertTrue(new AccountDAO(mConn).findAll().size() >= 3);
  }

  @Test
  public void testInsert() throws Exception {
    int initialSize = new AccountDAO(mConn).findAll().size();
    Bank bank = new BankDAO(mConn).findById(1);
    Customer cust = new CustomerDAO(mConn).findById(1);
    Account acc = new Account();
    acc.setAccountType(AccountType.CREDIT);
    acc.setAmount(100);
    acc.setBank(bank);
    acc.setCurrency(Currency.GBP);
    acc.setCustomer(cust);
    acc.setIBAN("XXXXXXXXXXXXXXXXX");
    AccountDAO accDAO = new AccountDAO(mConn);
    Account savedAcc = accDAO.insert(acc);
    assertTrue(savedAcc.getId() != 0);
    acc.setId(savedAcc.getId());
    assertEquals(acc, savedAcc);
    assertEquals(initialSize, new AccountDAO(mConn).findAll().size() -1);
  }

  @Test
  public void testUpdate() throws Exception {
    AccountDAO accDAO = new AccountDAO(mConn);
    Account acc = accDAO.findById(1);
    double amount = acc.getAmount();
    acc.setAmount(amount + 33);
    accDAO.update(acc);
    assertEquals((int)(amount+33), (int)accDAO.findById(1).getAmount());
  }

  @Test
  public void testDelete() throws Exception {
  }

}

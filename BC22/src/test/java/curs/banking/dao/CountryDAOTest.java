package curs.banking.dao;

import org.junit.Assert;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import curs.banking.dao.CountryDAO;
import curs.banking.dao.DAOException;
import curs.banking.model.Country;

public class CountryDAOTest {
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
    CountryDAO cdao = new CountryDAO(mConn);
    Country c = cdao.findById(1);
    Assert.assertNotNull(c);
    Assert.assertEquals("ROMANIA", c.getName());
    c = cdao.findById(111);
    Assert.assertNull(c);
  }

  @Test
  public void testInsertFindById() {
    CountryDAO cdao = new CountryDAO(mConn);
    Country c = new Country();
    c.setName("Bahrein");
    Country c1 = cdao.insert(c);
    Assert.assertEquals("Bahrein", c1.getName());
    Country c2 = cdao.findById(c1.getId());
    Assert.assertEquals(c1, c2);
  }

  @Test
  public void testFindByName() {
  }

  @Test
  public void testFindAll() {
    CountryDAO cdao = new CountryDAO(mConn);
    Collection<Country> countries = cdao.findAll();
    Assert.assertNotNull(countries);
    Assert.assertFalse(countries.isEmpty());
  }

  @Test
  public void testInsert() {
    CountryDAO cdao = new CountryDAO(mConn);
    Country c = new Country();
    c.setName("Niger");
    Country c1 = cdao.insert(c);
    Assert.assertEquals("Niger", c1.getName());
  }

  @Test
  public void testUpdate() {
    CountryDAO cdao = new CountryDAO(mConn);
    Country c = cdao.findById(1);
    c.setName("Romaniaaaa");
    cdao.update(c);
    c = cdao.findById(1);
    Assert.assertEquals("Romaniaaaa", c.getName());

  }

  @Test(expected = DAOException.class)
  public void testDelete() {
    //throw new NullPointerException();
    CountryDAO cdao = new CountryDAO(mConn);
    Country c = cdao.findById(1);
    cdao.delete(c);
    Assert.fail("Nu trebuia sa ajung aici");
  }

}

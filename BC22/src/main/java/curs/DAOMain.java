package curs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import curs.banking.dao.CityDAO;
import curs.banking.dao.CountryDAO;
import curs.banking.model.City;
import curs.banking.model.Country;

//TEST
public class DAOMain {
  static final String DB_URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE";

  public static void main(String[] args) throws SQLException {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(DB_URL, "SA", "");
      CountryDAO countryDAO = new CountryDAO(conn);
      System.out.println(countryDAO.findById(1));
      System.out.println(countryDAO.findAll());
      Country c = new Country();
      c.setName("Usa");
      c = countryDAO.insert(c);
      System.out.println(c);
      System.out.println(countryDAO.findAll());
      c.setName("USA");
      c = countryDAO.update(c);
      System.out.println(countryDAO.findAll());

      CityDAO cityDAO = new CityDAO(conn);
      Country countryUSA = countryDAO.findByName("USA");
      City city = new City();
      city.setName("San Francisco");
      city.setCountry(countryUSA);
      System.out.println(cityDAO.insert(city));
      System.out.println(cityDAO.findAll());
    } finally {
      conn.close();
    }
  }

}

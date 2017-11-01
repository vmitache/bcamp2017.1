package curs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import curs.banking.model.City;
import curs.banking.model.Country;

public class Main {
  static final String DB_URL = "jdbc:h2:~/test";

  public static void main(String[] args) throws SQLException {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      List<Country> countries = new ArrayList<>();
      conn = DriverManager.getConnection(DB_URL, "SA", "");
      stmt = conn.createStatement();
      rs = stmt.executeQuery("SELECT ID,NAME from BANK.COUNTRY");
      while (rs.next()) {
        long id = rs.getLong(1);
        String name = rs.getString(2);
        Country c = new Country(id, name);
        countries.add(c);
      }
      rs.close();
      rs = null;
      System.out.println(countries);
      List<City> cities = new ArrayList<>();
      rs = stmt.executeQuery("SELECT ID,NAME,COUNTRY_ID FROM BANK.CITY");
      while (rs.next()) {
        long id = rs.getLong(1);
        String name = rs.getString(2);
        long countryId = rs.getLong(3);
        Optional<Country> oc = countries.stream().filter(c -> c.getId() == countryId).findFirst();
        City city = new City();
        city.setId(id);
        city.setName(name);
        //if(oc.isPresent()) {
        //  city.setCountry(oc.get());
        //}
        city.setCountry(oc.orElse(null));
        cities.add(city);
      }
      System.out.println(countries);

    } finally {
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
      if (conn != null) {
        conn.close();
      }
    }

  }

}

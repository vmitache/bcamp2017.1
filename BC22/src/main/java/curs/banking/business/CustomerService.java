package curs.banking.business;

import java.sql.Connection;
import java.sql.DriverManager;

import curs.banking.dao.AddressDAO;
import curs.banking.dao.CustomerDAO;
import curs.banking.dao.SQLUtils;
import curs.banking.model.Customer;

public class CustomerService {
  static final String DB_URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE";

  public Connection getConnection() throws Exception {
    return DriverManager.getConnection(DB_URL, "SA", "");
  }

  public Customer createCustomer(Customer pCustomer, boolean pCommit) throws Exception {
    Connection conn = getConnection();
    try {
      conn.setAutoCommit(false);
      AddressDAO addressDAO = new AddressDAO(conn);
      if (false) {
        throw new NullPointerException();
      }

      CustomerDAO custDAO = new CustomerDAO(conn);
      pCustomer.setAddress(addressDAO.insert(pCustomer.getAddress()));// BE AWARE
      Customer cust = custDAO.insert(pCustomer);
      if (pCommit) {
        conn.commit();
      } else {
        conn.rollback();
      }
      return cust;
    } finally {
      SQLUtils.closeQuietly(conn);
    }
  }

}

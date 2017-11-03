package curs.banking.business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import curs.banking.dao.AccountDAO;
import curs.banking.dao.AddressDAO;
import curs.banking.dao.CustomerDAO;
import curs.banking.dao.DAOException;
import curs.banking.dao.SQLUtils;
import curs.banking.dao.TransactionDAO;
import curs.banking.model.Account;
import curs.banking.model.Customer;
import curs.banking.model.Transaction;
import curs.banking.model.TransactionType;

public class CustomerService {
  // ASSOCIATES ONE CONNECTION TO INE THREAD
  
  private static ThreadLocal<Connection> mThreadConn = new ThreadLocal<Connection>() {
    protected Connection initialValue() {
      try {
        return DriverManager.getConnection(DB_URL, "SA", "");
      } catch (SQLException e) {
        e.printStackTrace();
        return null;
      }
    }
  };

  static final String DB_URL = "jdbc:h2:~/test;AUTO_SERVER=TRUE";

  public Connection getConnection() throws Exception {
    return DriverManager.getConnection(DB_URL, "SA", "");
    //return mThreadConn.get();
  }
  // SE DAU
  // ACCOUNT1 - findById (1)
  // ACCOUNT2 - findById (2)
  // DE FACUT TRANSACTIE INTRE ACCOUNT1 si ACCOUNT2
  // 1 - Transaction('D',ACCOUNT1,SUMA)
  // 2 - ACCOUNT1.SOLD = ACCOUNT1.SOLD - SUMA
  // 3 - Transaction('C',ACCOUNT2, SUMA)
  // 4 - ACCOUNT2.SOLD = ACCOUN2.SOLA + SUMA

  public boolean transferMoney(long pAccountDebitId, long pAccountCreditId, double pAmount)
      throws SQLException, Exception {
    try (Connection conn = getConnection()) {
      conn.setAutoCommit(false);
      AccountDAO accDAO = new AccountDAO(conn);
      TransactionDAO transDAO = new TransactionDAO(conn);
      Account accDebit = accDAO.findById(pAccountDebitId);
      Account accCredit = accDAO.findById(pAccountCreditId);
      if (!accDebit.getCurrency().equals(accCredit.getCurrency())) {
        throw new DAOException("Different currency type");
      }
      if (accCredit.getAmount() >= pAmount) {
        long transactionTS = new Date().getTime();
        Transaction trans1 = new Transaction();
        trans1.setAccount(accCredit);
        trans1.setAmount(pAmount);
        trans1.setTransactionTime(new Timestamp(transactionTS));
        trans1.setTransactionType(TransactionType.D);

        Transaction trans2 = new Transaction();
        trans2.setAccount(accDebit);
        trans2.setAmount(pAmount);
        trans2.setTransactionTime(new Timestamp(transactionTS));
        trans2.setTransactionType(TransactionType.C);

        accCredit.setAmount(accCredit.getAmount() - pAmount);
        accDebit.setAmount(accDebit.getAmount() + pAmount);
        // ATOMIC!!!!
        transDAO.insert(trans1);
        transDAO.insert(trans2);
        accDAO.update(accCredit);
        accDAO.update(accDebit);
        // ATOMIC!!!
        conn.commit();
        return true;
      } else {
        conn.rollback();
        return false;
      }
    }
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

  public static void main(String[] pArgs) throws Exception {
    CustomerService cs = new CustomerService();
    System.out.println(cs.getConnection());
    for (int i = 0; i < 10; i++) {
      Thread.sleep(100);
    }
    System.out.println(cs.getConnection());
    new Thread(() -> {
      CustomerService cs1 = new CustomerService();
      try {
        System.out.println(cs1.getConnection());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      CustomerService cs1 = new CustomerService();
      try {
        System.out.println(cs1.getConnection());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      CustomerService cs1 = new CustomerService();
      try {
        System.out.println(cs1.getConnection());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
  }

}

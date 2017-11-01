package curs.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import curs.banking.model.Account;
import curs.banking.model.AccountType;
import curs.banking.model.Bank;
import curs.banking.model.Currency;

public class AccountDAO implements BasicDAO<Account> {
  protected Connection mConnection;
  private CustomerDAO mCustomerDAO;
  private BankDAO mBankDAO;

  public AccountDAO(Connection pConnection) {
    mConnection = pConnection;
    mCustomerDAO = new CustomerDAO(pConnection);
    mBankDAO = new BankDAO(mConnection);
  }

  // ResultSet pRS pozitionat pe randul necesar !!!!
  protected Account loadFromResultSet(ResultSet pRS) throws SQLException {
    Account account = new Account();
    account.setId(pRS.getLong(1));
    account.setIBAN(pRS.getString(2));
    long bankID = pRS.getLong(3);
    if (!pRS.wasNull()) {
      account.setBank(mBankDAO.findById(bankID));
    }
    long custID = pRS.getLong(4);
    if (!pRS.wasNull()) {
      account.setCustomer(mCustomerDAO.findById(custID));
    }
    account.setAmount(pRS.getDouble(5));
    String accType = pRS.getString(6);
    /**
     * switch(accType) { case "DEBIT": account.setAccountType(AccountType.DEBIT);
     * break; case "CREDIT": account.setAccountType(AccountType.CREDIT); break; case
     * "SAVINGS": account.setAccountType(AccountType.SAVINGS); break; }
     */
    for (AccountType at : AccountType.values()) {
      if (at.name().equals(accType)) {
        account.setAccountType(at);
        break;
      }
    }
    int cid = pRS.getInt(7);
    for (Currency c : Currency.values()) {
      if (cid == c.getId()) {
        account.setCurrency(c);
      }
    }
    return account;

  }

  @Override
  public Account findById(long pId) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement(
          "SELECT ID,IBAN,BANK_ID,CUSTOMER_ID,AMOUNT,ACCOUNT_TYPE,CURRENCY_ID FROM BANK.ACCOUNT WHERE ID=?");
      stmt.setLong(1, pId);
      rs = stmt.executeQuery();
      if (rs.next()) {
        return loadFromResultSet(rs);
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
  public Collection<Account> findAll() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Account> result = new ArrayList<>();
    try {
      stmt = mConnection
          .prepareStatement("SELECT ID,IBAN,BANK_ID,CUSTOMER_ID,AMOUNT,ACCOUNT_TYPE,CURRENCY_ID FROM BANK.ACCOUNT");
      rs = stmt.executeQuery();
      while (rs.next()) {
        result.add(loadFromResultSet(rs));
      }
      return result;
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);

    }

  }

  @Override
  public Account insert(Account pEntity) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = mConnection.prepareStatement("INSERT INTO BANK.ACCOUNT(IBAN,BANK_ID,CUSTOMER_ID,AMOUNT,ACCOUNT_TYPE,CURRENCY_ID) VALUES(?,?,?,?,?,?,?)",
          Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, pEntity.getIBAN());
      stmt.setLong(2, pEntity.getBank().getId());
      stmt.setLong(3, pEntity.getCustomer().getId());
      stmt.setDouble(4, pEntity.getAmount());
      stmt.setString(5, pEntity.getAccountType().name());
      stmt.setLong(6, pEntity.getCurrency().getId());
      stmt.executeUpdate();
      rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        long id = rs.getLong(1);
        return findById(id);
      } else {
        throw new DAOException("Id not found in insert");
      }
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      SQLUtils.closeQuietly(rs, stmt);

    }

  }

  @Override
  public Account update(Account pEntity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(Account pEntity) {
    // TODO Auto-generated method stub

  }

}

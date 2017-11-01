package curs.banking.model;

import java.sql.Timestamp;

public class Transaction {
  private long mId;
  private Account mAccount;
  private TransactionType mTransactionType;
  private double mAmount;
  private Timestamp mTransactionTime;

  public long getId() {
    return mId;
  }

  public void setId(long pId) {
    mId = pId;
  }

  public Account getAccount() {
    return mAccount;
  }

  public void setAccount(Account pAccount) {
    mAccount = pAccount;
  }

  public TransactionType getTransactionType() {
    return mTransactionType;
  }

  public void setTransactionType(TransactionType pTransactionType) {
    mTransactionType = pTransactionType;
  }

  public double getAmount() {
    return mAmount;
  }

  public void setAmount(double pAmount) {
    mAmount = pAmount;
  }

  public Timestamp getTransactionTime() {
    return mTransactionTime;
  }

  public void setTransactionTime(Timestamp pTransactionTime) {
    mTransactionTime = pTransactionTime;
  }

  @Override
  public String toString() {
    return "Transaction [mId=" + mId + ", mAccount=" + mAccount + ", mTransactionType=" + mTransactionType
        + ", mAmount=" + mAmount + ", mTransactionTime=" + mTransactionTime + "]";
  }

}

package curs.banking.model;

public class Account {
  private long mId;
  private String mIBAN;
  private Bank mBank;
  private Customer mCustomer;
  private double mAmount;
  private AccountType mAccountType;
  private Currency mCurrency;

  public long getId() {
    return mId;
  }

  public void setId(long pId) {
    mId = pId;
  }

  public String getIBAN() {
    return mIBAN;
  }

  public void setIBAN(String pIBAN) {
    mIBAN = pIBAN;
  }

  public Bank getBank() {
    return mBank;
  }

  public void setBank(Bank pBank) {
    mBank = pBank;
  }

  public Customer getCustomer() {
    return mCustomer;
  }

  public void setCustomer(Customer pCustomer) {
    mCustomer = pCustomer;
  }

  public double getAmount() {
    return mAmount;
  }

  public void setAmount(double pAmount) {
    mAmount = pAmount;
  }

  public AccountType getAccountType() {
    return mAccountType;
  }

  public void setAccountType(AccountType pAccountType) {
    mAccountType = pAccountType;
  }

  public Currency getCurrency() {
    return mCurrency;
  }

  public void setCurrency(Currency pCurrency) {
    mCurrency = pCurrency;
  }

  @Override
  public String toString() {
    return "Account [mId=" + mId + ", mIBAN=" + mIBAN + ", mBank=" + mBank + ", mCustomer=" + mCustomer + ", mAmount="
        + mAmount + ", mAccountType=" + mAccountType + ", mCurrency=" + mCurrency + "]";
  }

}

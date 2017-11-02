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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((mAccountType == null) ? 0 : mAccountType.hashCode());
    long temp;
    temp = Double.doubleToLongBits(mAmount);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((mBank == null) ? 0 : mBank.hashCode());
    result = prime * result + ((mCurrency == null) ? 0 : mCurrency.hashCode());
    result = prime * result + ((mCustomer == null) ? 0 : mCustomer.hashCode());
    result = prime * result + ((mIBAN == null) ? 0 : mIBAN.hashCode());
    result = prime * result + (int) (mId ^ (mId >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Account other = (Account) obj;
    if (mAccountType != other.mAccountType)
      return false;
    if (Double.doubleToLongBits(mAmount) != Double.doubleToLongBits(other.mAmount))
      return false;
    if (mBank == null) {
      if (other.mBank != null)
        return false;
    } else if (!mBank.equals(other.mBank))
      return false;
    if (mCurrency != other.mCurrency)
      return false;
    if (mCustomer == null) {
      if (other.mCustomer != null)
        return false;
    } else if (!mCustomer.equals(other.mCustomer))
      return false;
    if (mIBAN == null) {
      if (other.mIBAN != null)
        return false;
    } else if (!mIBAN.equals(other.mIBAN))
      return false;
    if (mId != other.mId)
      return false;
    return true;
  }
  
  

}

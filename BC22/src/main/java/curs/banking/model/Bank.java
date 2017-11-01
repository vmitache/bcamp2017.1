package curs.banking.model;

public class Bank {
  private long mId;
  private String mName;
  private Address mAdress;
  private String mFiscalCode;

  public long getId() {
    return mId;
  }

  public void setId(long pId) {
    mId = pId;
  }

  public String getName() {
    return mName;
  }

  public void setName(String pName) {
    mName = pName;
  }

  public Address getAdress() {
    return mAdress;
  }

  public void setAdress(Address pAdress) {
    mAdress = pAdress;
  }

  public String getFiscalCode() {
    return mFiscalCode;
  }

  public void setFiscalCode(String pFiscalCode) {
    mFiscalCode = pFiscalCode;
  }

  @Override
  public String toString() {
    return "Bank [mId=" + mId + ", mName=" + mName + ", mAdress=" + mAdress + ", mFiscalCode=" + mFiscalCode + "]";
  }

}

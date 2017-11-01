package curs.banking.model;

public class Customer {
  private long mId;
  private String mName;
  private String mSSN;
  private Address mAddress;
  private Integer mVarsta;
  private SexEnum mSex;

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

  public String getSSN() {
    return mSSN;
  }

  public void setSSN(String pSSN) {
    mSSN = pSSN;
  }

  public Address getAddress() {
    return mAddress;
  }

  public void setAddress(Address pAddress) {
    mAddress = pAddress;
  }

  public Integer getVarsta() {
    return mVarsta;
  }

  public void setVarsta(Integer pVarsta) {
    mVarsta = pVarsta;
  }

  public SexEnum getSex() {
    return mSex;
  }

  public void setSex(SexEnum pSex) {
    mSex = pSex;
  }

  @Override
  public String toString() {
    return "Customer [mId=" + mId + ", mName=" + mName + ", mSSN=" + mSSN + ", mAddress=" + mAddress + ", mVarsta="
        + mVarsta + ", mSex=" + mSex + "]";
  }

}

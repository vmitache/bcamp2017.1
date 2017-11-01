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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((mAddress == null) ? 0 : mAddress.hashCode());
    result = prime * result + (int) (mId ^ (mId >>> 32));
    result = prime * result + ((mName == null) ? 0 : mName.hashCode());
    result = prime * result + ((mSSN == null) ? 0 : mSSN.hashCode());
    result = prime * result + ((mSex == null) ? 0 : mSex.hashCode());
    result = prime * result + ((mVarsta == null) ? 0 : mVarsta.hashCode());
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
    Customer other = (Customer) obj;
    if (mAddress == null) {
      if (other.mAddress != null)
        return false;
    } else if (!mAddress.equals(other.mAddress))
      return false;
    if (mId != other.mId)
      return false;
    if (mName == null) {
      if (other.mName != null)
        return false;
    } else if (!mName.equals(other.mName))
      return false;
    if (mSSN == null) {
      if (other.mSSN != null)
        return false;
    } else if (!mSSN.equals(other.mSSN))
      return false;
    if (mSex != other.mSex)
      return false;
    if (mVarsta == null) {
      if (other.mVarsta != null)
        return false;
    } else if (!mVarsta.equals(other.mVarsta))
      return false;
    return true;
  }

  
}

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((mAdress == null) ? 0 : mAdress.hashCode());
    result = prime * result + ((mFiscalCode == null) ? 0 : mFiscalCode.hashCode());
    result = prime * result + (int) (mId ^ (mId >>> 32));
    result = prime * result + ((mName == null) ? 0 : mName.hashCode());
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
    Bank other = (Bank) obj;
    if (mAdress == null) {
      if (other.mAdress != null)
        return false;
    } else if (!mAdress.equals(other.mAdress))
      return false;
    if (mFiscalCode == null) {
      if (other.mFiscalCode != null)
        return false;
    } else if (!mFiscalCode.equals(other.mFiscalCode))
      return false;
    if (mId != other.mId)
      return false;
    if (mName == null) {
      if (other.mName != null)
        return false;
    } else if (!mName.equals(other.mName))
      return false;
    return true;
  }

}

package curs.banking.model;

public class Address {
  private long mId;
  private City mCity;
  private String mStreet;
  private String mNumber;
  private String mPostalCode;

  public long getId() {
    return mId;
  }

  public void setId(long pId) {
    mId = pId;
  }

  public City getCity() {
    return mCity;
  }

  public void setCity(City pCity) {
    mCity = pCity;
  }

  public String getStreet() {
    return mStreet;
  }

  public void setStreet(String pStreet) {
    mStreet = pStreet;
  }

  public String getNumber() {
    return mNumber;
  }

  public void setNumber(String pNumber) {
    mNumber = pNumber;
  }

  public String getPostalCode() {
    return mPostalCode;
  }

  public void setPostalCode(String pPostalCode) {
    mPostalCode = pPostalCode;
  }

  @Override
  public String toString() {
    return "Address [mId=" + mId + ", mCity=" + mCity + ", mStreet=" + mStreet + ", mNumber=" + mNumber
        + ", mPostalCode=" + mPostalCode + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((mCity == null) ? 0 : mCity.hashCode());
    result = prime * result + (int) (mId ^ (mId >>> 32));
    result = prime * result + ((mNumber == null) ? 0 : mNumber.hashCode());
    result = prime * result + ((mPostalCode == null) ? 0 : mPostalCode.hashCode());
    result = prime * result + ((mStreet == null) ? 0 : mStreet.hashCode());
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
    Address other = (Address) obj;
    if (mCity == null) {
      if (other.mCity != null)
        return false;
    } else if (!mCity.equals(other.mCity))
      return false;
    if (mId != other.mId)
      return false;
    if (mNumber == null) {
      if (other.mNumber != null)
        return false;
    } else if (!mNumber.equals(other.mNumber))
      return false;
    if (mPostalCode == null) {
      if (other.mPostalCode != null)
        return false;
    } else if (!mPostalCode.equals(other.mPostalCode))
      return false;
    if (mStreet == null) {
      if (other.mStreet != null)
        return false;
    } else if (!mStreet.equals(other.mStreet))
      return false;
    return true;
  }

  
}

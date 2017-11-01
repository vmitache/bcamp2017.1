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

}

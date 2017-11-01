package curs.banking.model;

public class City {
  private long mId;
  private String mName;
  private Country mCountry;

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

  public Country getCountry() {
    return mCountry;
  }

  public void setCountry(Country pCountry) {
    mCountry = pCountry;
  }

  @Override
  public String toString() {
    return "City [mId=" + mId + ", mName=" + mName + ", mCountry=" + mCountry + "]";
  }

}

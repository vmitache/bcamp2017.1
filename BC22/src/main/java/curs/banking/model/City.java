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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((mCountry == null) ? 0 : mCountry.hashCode());
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
    City other = (City) obj;
    if (mCountry == null) {
      if (other.mCountry != null)
        return false;
    } else if (!mCountry.equals(other.mCountry))
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

package curs.banking.model;

public class Country {
  private long mId;
  private String mName;

  
  
  public Country() {
    super();
  }

  public Country(long pId, String pName) {
    super();
    mId = pId;
    mName = pName;
  }

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

  @Override
  public String toString() {
    return "Country [mId=" + mId + ", mName=" + mName + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
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
    Country other = (Country) obj;
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

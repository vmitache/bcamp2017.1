package curs.banking.model;

public enum Currency {
  RON(1L), EUR(2L), USD(3L), GBP(4L);

  private long mId;
  
  private Currency(long pId) {
    mId = pId;
  }

  public long getId() {
    return mId;
  }
   
}

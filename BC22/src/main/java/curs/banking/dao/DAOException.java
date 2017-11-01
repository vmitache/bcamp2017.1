package curs.banking.dao;

public class DAOException extends RuntimeException {
  public DAOException(String pMsg) {
    super(pMsg);
  }

  public DAOException(String pMsg, Throwable pCause) {
    super(pMsg, pCause);
  }

  public DAOException(Throwable pCause) {
    super(pCause);
  }

}

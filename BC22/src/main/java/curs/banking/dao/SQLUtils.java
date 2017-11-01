package curs.banking.dao;

public class SQLUtils {
  public static void closeQuietly(AutoCloseable... pCloseables) {
    for (AutoCloseable c : pCloseables) {
      if (c != null) {
        try {
          c.close();
        } catch (Exception e) {
          // log or ignore, we can't do anything about it really
        }
      }
    }
  }
}

package de.niklashere.hidenseek.libary;

import de.niklashere.hidenseek.files.languages.Variablelist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates a connection to a mysql database.
 *
 * @author Niklashere
 * @since 03.08.2021
 */
public class MysqlManager {
  private static String HOST = Fileaccess.getString("MySQL.host", Fileaccess.getConfig());
  private static String DATABASE = Fileaccess.getString("MySQL.database", Fileaccess.getConfig());
  private static String USER = Fileaccess.getString("MySQL.user", Fileaccess.getConfig());
  private static String PASSWORD = Fileaccess.getString("MySQL.password", Fileaccess.getConfig());
  private static Connection con;

  /**
   * Needed parameter to establish a MySQL connection.
   * 
   * @param host     DB Host
   * @param database DB name
   * @param user     DB user
   * @param password DB password
   */
  public MysqlManager(String host, String database, String user, String password) {
    HOST = host;
    DATABASE = database;
    USER = user;
    PASSWORD = password;

    connect();
  }

  /**
   * Method to establish a connection.
   */
  public static void connect() {
    try {
      con = java.sql.DriverManager.getConnection(
          "jdbc:mysql://" + HOST + "/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);

      update("CREATE TABLE IF NOT EXISTS Stats(UUID varchar(64),"
          + " cought int, found int, wins int, plays int, points int)");
      update("CREATE TABLE IF NOT EXISTS Languages(UUID varchar(64)," + " language varchar(16))");
      System.out.println(LanguageManager.getMessage(Variablelist.console_mysql_connected));
    } catch (SQLException e) {
      System.out
          .println(LanguageManager.getMessage(Variablelist.console_mysql_error) + e.getMessage());
    }
  }

  /**
   * Method to close a connection.
   */
  public static void close() {
    try {
      if (con != null) {
        con.close();
        System.out.println(LanguageManager.getMessage(Variablelist.console_mysql_closed));
      }
    } catch (SQLException e) {
      System.out
          .println(LanguageManager.getMessage(Variablelist.console_mysql_error) + e.getMessage());
    }
  }

  /**
   * Executes the given SQL statement, which may be an INSERT, UPDATE, or DELETE
   * statement or an SQL statement that returns nothing, such as an SQL DDL
   * statement.
   *
   * @param qry query to execute
   */
  public static void update(String qry) {
    try {
      Statement st = con.createStatement();
      st.executeUpdate(qry);
      st.close();
    } catch (SQLException e) {
      connect();
      System.err.println(e);
    }
  }

  /**
   * Executes the given SQL statement, which returns a single ResultSet object.
   * 
   * @param qry query to execute
   * @return result
   */
  public static ResultSet query(String qry) {
    ResultSet rs = null;
    try (Statement st = con.createStatement()) {
      rs = st.executeQuery(qry);
    } catch (SQLException e) {
      connect();
      System.err.println(e);
    }
    return rs;
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Forms.Login;
import Utils.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author lagos
 */
public class Year {
  public static boolean checkYear(String year, String orientation) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    int oricod = Orientation.getOrientationcod(orientation);

    String query = "SELECT * FROM `ANOS` WHERE `ANO_NOMBRE`='" + year + "' AND `ANO_ORICOD`='" + oricod + "' AND 'ANO_FECHAFIN' > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, todayDate);
      preparedStatemnet.execute();
      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();

      if(rs.next()){
        return true;
      }

    } catch (SQLException e) {
      System.out.println("ERROR: " + e);
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    return false;
  }
  
  public static int getYearcod(String year) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "SELECT * FROM `ANOS` WHERE `ANO_NOMBRE`='" + year + "' AND `ANO_FECHAFIN` > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      if(rs.next()){
        return (int) rs.getInt("ANO_COD");
      }

    } catch (SQLException e) {
      System.out.println("ERROR: " + e);
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    return 0;
  }
  
  public static ArrayList getYears() {
    ArrayList years = new ArrayList(); 
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "SELECT * FROM `ANOS` WHERE `ANO_FECHAFIN` > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      while(rs.next()){
        String year = rs.getString("ANO_NOMBRE");
        if(!years.contains(year)) years.add(year);
      }

    } catch (SQLException e) {
      System.out.println("ERROR: " + e);
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    return years; 
  }

  public static void delete(String year, String orientation) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    int oricod = Orientation.getOrientationcod(orientation);
    
    String query = "UPDATE ANOS SET ANO_FECHAFIN=? WHERE ANO_NOMBRE='" + year + "' AND ANO_ORICOD='" + oricod + "';";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, todayDate);
      preparedStatemnet.execute();
      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();

    } catch (SQLException e) {
      System.out.println("ERROR: " + e);
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  static String getYearName(int year) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "SELECT * FROM `ANOS` WHERE `ANO_COD`='" + year + "' AND `ANO_FECHAFIN` > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      if(rs.next()){
        return (String) rs.getString("ANO_NOMBRE");
      }

    } catch (SQLException e) {
      System.out.println("ERROR: " + e);
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    return "";
  }
}

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
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author lagos
 */
public class Division {
  public static ArrayList getDivisions() {
    ArrayList divisions = new ArrayList(); 
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "SELECT * FROM `DIVISIONES` WHERE `DIV_FECHAFIN` > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      while(rs.next()){
        String division = rs.getString("DIV_NOMBRE");
        if(!divisions.contains(division)) divisions.add(division);
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
    
    return divisions; 
  }
  
  public static int getDivcod (String division) {
      BasicDataSource bs = Config.setDBParams();
      Connection connection = null;
      java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

      String query = "SELECT * FROM `DIVISIONES` WHERE `DIV_NOMBRE`='" + division + "' AND 'DIV_FECHAFIN' > ?;";

      try {
        connection = bs.getConnection();
        PreparedStatement preparedStatemnet = connection.prepareStatement(query);
        preparedStatemnet.setDate(1, todayDate);
        preparedStatemnet.execute();
        ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();

        if(rs.next()){
          return (int) rs.getInt("DIV_COD");
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
}

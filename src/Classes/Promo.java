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
public class Promo {
  public static ArrayList getPromos(String userType, int usercod) {

    if(userType.equals("Alumno")) return getPromosByStudent(usercod); 
    if(userType.equals("Profesor")) return getPromosByTeacher(usercod); 
    
    ArrayList years = new ArrayList();
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `CURSOSMATERIAS_ALUMNOS`;";
    
    try {                                                          

      java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
      
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.execute();

      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();
      
      while(rs.next()) {
        int year = rs.getInt("CURMATALU_ANOFECHA");
        if (!years.contains(year)) years.add(year);
      }
      
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }   
   
    return years;
  }

  private static ArrayList getPromosByTeacher(int usercod) {
    
    ArrayList years = new ArrayList();
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `CURSOSMATERIAS_PROFESORES` WHERE `CURMATPRO_PERCOD`='" + usercod + "';";
    
    try {                                                          

      java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
      
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.execute();

      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();
      
      while(rs.next()) {
        int year = rs.getInt("CURMATPRO_ANOFECHA");
        if (!years.contains(year)) years.add(year);
      }
      
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }   
   
    return years;
  }

  private static ArrayList getPromosByStudent(int usercod) {
    ArrayList years = new ArrayList();
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `CURSOSMATERIAS_ALUMNOS` WHERE `CURMATALU_ALUMNOPERCOD`='" + usercod + "';";
    
    try {                                                          

      java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
      
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.execute();

      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();
      
      while(rs.next()) {
        int year = rs.getInt("CURMATALU_ANOFECHA");
        if (!years.contains(year)) years.add(year);
      }
      
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }   
   
    return years;
  }
}

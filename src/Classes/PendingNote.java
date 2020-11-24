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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author lagos
 */
public class PendingNote {

  static void newPendingNote(int studentCod, int oricod, int yearcod, int curcod, int matcod, int teacherCod, int prdcod) throws ParseException {
    int forcod = Subject.getForCod(matcod);
    if (updatePendingNote(studentCod, oricod, yearcod, curcod, matcod, forcod, teacherCod, prdcod)) return;  
    boolean isWorkshop = forcod != Subject.getTypeCodeByName("Formación General");
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null; 
    
    String query = "INSERT INTO `PENDIENTES` (`PEN_ALUMNOPERCOD`, `PEN_ALUMNOCATCOD`, `PEN_ALUMNOFECHAINICIO`, `PEN_CURORICOD`, `PEN_CURANOCOD`, `PEN_CURCOD`, `PEN_MATCOD`, `PEN_FORCOD`, `PEN_PRDCOD`, `PEN_PROFCOD`, `PEN_TALLER`, `PEN_FECHAFIN`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, studentCod);
      preparedStatement.setInt(2, Categorie.getCatcod("Alumno"));    
      preparedStatement.setDate(3, (java.sql.Date) User.getCategorieStartedDateByUsercod(studentCod, Categorie.getCatcod("Alumno")));
      preparedStatement.setInt(4, oricod);
      preparedStatement.setInt(5, yearcod);
      preparedStatement.setInt(6, curcod);
      preparedStatement.setInt(7, matcod);
      preparedStatement.setInt(8, forcod);
      preparedStatement.setInt(9, prdcod);
      preparedStatement.setInt(10, teacherCod);
      preparedStatement.setBoolean(11, isWorkshop);
      preparedStatement.setDate(12, neverDate);
      preparedStatement.execute();
      
    } catch(SQLException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      
    } finally {
      if(connection != null) try {
          connection.close();
        } catch (SQLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
  }
  
  public static boolean updatePendingNote(int studentCod, int oricod, int yearcod, int curcod, int matcod, int forcod, int teacherCod, int prdcod) {
    if(!previusPending(studentCod, oricod, yearcod, curcod, matcod, forcod, teacherCod)) return false;
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    String query = "UPDATE `PENDIENTES` SET `PEN_PRDCOD`=?  WHERE `PEN_CURORICOD`=? AND `PEN_CURANOCOD`=? AND `PEN_CURCOD`=? AND `PEN_MATCOD`=? AND `PEN_FORCOD`=? AND `PEN_PRDCOD`!=?  AND `PEN_PROFCOD`=? AND `PEN_FECHAFIN`>?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, prdcod);
      preparedStatement.setInt(2, oricod);    
      preparedStatement.setInt(3, yearcod);
      preparedStatement.setInt(4, curcod);
      preparedStatement.setInt(5, matcod);
      preparedStatement.setInt(6, forcod);
      preparedStatement.setInt(7, prdcod);
      preparedStatement.setInt(8, studentCod);
      preparedStatement.setInt(9, teacherCod);
      preparedStatement.setDate(10, todayDate);
      
      preparedStatement.execute();
      return true;
      
    } catch(SQLException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      
    } finally {
      if(connection != null) try {
          connection.close();
        } catch (SQLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    return false;
  }

  private static boolean previusPending(int studentCod, int oricod, int yearcod, int curcod, int matcod, int forcod, int teacherCod) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

    String query = "SELECT * FROM `PENDIENTES` WHERE `PEN_CURORICOD`=? AND `PEN_CURANOCOD`=? AND `PEN_CURCOD`=? AND `PEN_MATCOD`=? AND `PEN_ALUMNOPERCOD`=? AND `PEN_PROFCOD`=? AND `PEN_FECHAFIN`>?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, oricod);    
      preparedStatement.setInt(2, yearcod);
      preparedStatement.setInt(3, curcod);
      preparedStatement.setInt(4, matcod);
      preparedStatement.setInt(5, studentCod);
      preparedStatement.setInt(6, teacherCod);
      preparedStatement.setDate(7, todayDate);
      
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();
      
      if(rs.next()) {
        return true;
      }

    } catch(SQLException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if(connection != null) try {
          connection.close();
        } catch (SQLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    return false;
  }

  static void removePendingNote(int studentCod, int oricod, int yearcod, int curcod, int matcod, int teacherCod) {
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    String query = "UPDATE `PENDIENTES` SET `PEN_FECHAFIN`=?  WHERE `PEN_CURORICOD`=? AND `PEN_CURANOCOD`=? AND `PEN_CURCOD`=? AND `PEN_MATCOD`=?  AND `PEN_ALUMNOPERCOD`=? AND `PEN_PROFCOD`=?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.setInt(2, oricod);    
      preparedStatement.setInt(3, yearcod);
      preparedStatement.setInt(4, curcod);
      preparedStatement.setInt(5, matcod);
      preparedStatement.setInt(6, studentCod);
      preparedStatement.setInt(7, teacherCod);
     
      preparedStatement.execute();
      
    } catch(SQLException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      
    } finally {
      if(connection != null) try {
          connection.close();
        } catch (SQLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }

  public static boolean hasPendingSubject(int studentCod, int oricod, int yearcod, int curcod, int matcod) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

    String query = "SELECT * FROM `PENDIENTES` WHERE `PEN_CURORICOD`=? AND `PEN_CURANOCOD`=? AND `PEN_CURCOD`=? AND `PEN_MATCOD`=? AND `PEN_ALUMNOPERCOD`=?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, oricod);    
      preparedStatement.setInt(2, yearcod);
      preparedStatement.setInt(3, curcod);
      preparedStatement.setInt(4, matcod);
      preparedStatement.setInt(5, studentCod);
      
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();
      
      if(rs.next()) {
        return true;
      }

    } catch(SQLException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if(connection != null) try {
          connection.close();
        } catch (SQLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    return false;
  }
  
  public static ArrayList getPendingSubjects(int studentCod) {
    ArrayList pendingSubjects = new ArrayList();
   
    pendingSubjects.add(0, 0); // general
    pendingSubjects.add(1, 0); // specific
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

    String query = "SELECT * FROM `PENDIENTES` WHERE `PEN_ALUMNOPERCOD`=? AND `PEN_FECHAFIN`>?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, studentCod);
      preparedStatement.setDate(2, todayDate);
      
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();
      
      while(rs.next()) {
        if((boolean) rs.getBoolean("PEN_TALLER") == false) {
          pendingSubjects.add(0, (int) pendingSubjects.get(0) + 1);
        } else {
          pendingSubjects.add(1, (int) pendingSubjects.get(1) + 1);
        }
      }
      
      return pendingSubjects;
    } catch(SQLException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if(connection != null) try {
          connection.close();
        } catch (SQLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    return null;
  }
}

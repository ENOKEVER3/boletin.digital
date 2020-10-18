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
public class Note {
  Date studentStartDat;
  int studentCod;
  int studentCatCod;
  int oricod;
  int yearcod;
  int curcod;
  int matcod;
  int forcod;
  int prdcod;
  int order;
  int yearDate;
  
  int value;
  int profCode;
  
  public Date getStudentStartDat() {
    return studentStartDat;
  }

  public void setStudentStartDat(Date studentStartDat) {
    this.studentStartDat = studentStartDat;
  }

  public int getStudentCod() {
    return studentCod;
  }

  public void setStudentCod(int studentCod) {
    this.studentCod = studentCod;
  }

  public int getStudentCatCod() {
    return studentCatCod;
  }

  public void setStudentCatCod(int studentCatCod) {
    this.studentCatCod = studentCatCod;
  }

  public int getOricod() {
    return oricod;
  }

  public void setOricod(int oricod) {
    this.oricod = oricod;
  }

  public int getYearcod() {
    return yearcod;
  }

  public void setYearcod(int yearcod) {
    this.yearcod = yearcod;
  }

  public int getCurcod() {
    return curcod;
  }

  public void setCurcod(int curcod) {
    this.curcod = curcod;
  }

  public int getMatcod() {
    return matcod;
  }

  public void setMatcod(int matcod) {
    this.matcod = matcod;
  }

  public int getForcod() {
    return forcod;
  }

  public void setForcod(int forcod) {
    this.forcod = forcod;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public int getYearDate() {
    return yearDate;
  }

  public void setYearDate(int yearDate) {
    this.yearDate = yearDate;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getProfCode() {
    return profCode;
  }

  public void setProfCode(int profCode) {
    this.profCode = profCode;
  }
  
  public int getPrdcod() {
    return prdcod;
  }

  public void setPrdcod(int prdcod) {
    this.prdcod = prdcod;
  }
  
  public static boolean createNote(int studentCod, int oricod, int anocod, int curcod, int matcod, int prdcod, int order, int value, int teacherCod) throws SQLException {
    ArrayList notes = new ArrayList();
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "INSERT INTO `NOTAS` (`NOT_ALUMNOFECHAINICIO`, `NOT_ALUMNOPERCOD`, `NOT_ALUMNOCATCOD`, `NOT_CURORICOD`, `NOT_CURANOCOD`, `NOT_CURCOD`, `NOT_MATCOD`, `NOT_FORCOD`, `NOT_PRDCOD`, `NOT_ORDEN`, `NOT_ANOFECHA`, `NOT_VALOR`, `NOT_PROFCOD`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, (java.sql.Date) User.getCategorieStartedDateByUsercod(studentCod, Categorie.getCatcod("Alumno")));
      preparedStatement.setInt(2, Categorie.getCatcod("Alumno"));
      preparedStatement.setInt(3, studentCod);
      preparedStatement.setInt(4, oricod);
      preparedStatement.setInt(5, anocod);
      preparedStatement.setInt(6, curcod);
      preparedStatement.setInt(7, matcod);
      preparedStatement.setInt(8, Subject.getForCod(matcod));
      preparedStatement.setInt(9, prdcod);
      preparedStatement.setInt(10, order);
      preparedStatement.setInt(11, (int) new Date().getYear());
      preparedStatement.setInt(12, value);
      preparedStatement.setInt(13, teacherCod);

      preparedStatement.execute();
      return true;
      
    } catch (SQLException e) {
      System.out.println("ERROR: " + e);
      return false;
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

}
  
  public static ArrayList getNote(int studentCod, int oricod, int anocod, int curcod, int matcod){
    ArrayList notes = new ArrayList();
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "SELECT * FROM `NOTAS` WHERE `NOT_ALUMNOPERCOD`=? AND `NOT_CURORICOD`=? AND `NOT_CURANOCOD`=? AND `NOT_CURCOD`=? AND `NOT_CURCOD`=?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, studentCod);
      preparedStatement.setInt(2, oricod);
      preparedStatement.setInt(3, anocod);
      preparedStatement.setInt(4, curcod);
      preparedStatement.setInt(5, matcod);
      
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      while(rs.next()){
        Note note = new Note();
        note.setStudentStartDat(rs.getDate("NOT_ALUMNOFECHAINICIO"));
        note.setStudentCod(rs.getInt("NOT_ALUMNOPERCOD"));
        note.setStudentCatCod(rs.getInt("NOT_ALUMNOCATCOD"));
        note.setOricod(rs.getInt("NOT_CURORICOD"));
        note.setYearcod(rs.getInt("NOT_CURANOCOD"));
        note.setCurcod(rs.getInt("NOT_CURCOD"));
        note.setMatcod(rs.getInt("NOT_MATCOD"));
        note.setForcod(rs.getInt("NOT_FORCOD"));
        note.setPrdcod(rs.getInt("NOT_PERCOD"));
        note.setOrder(rs.getInt("NOT_ORDEN"));
        note.setYearDate(rs.getInt("NOT_ANOFECHA"));
        note.setValue(rs.getInt("NOT_VALOR"));
        note.setProfCode(rs.getInt("NOT_PROFCOD"));
        
        notes.add(note);
      }
      
      if(notes != null) return notes;
    } catch (SQLException e) {
      System.out.println("ERROR: " + e);
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    return null;
  }
  
  public static boolean updateNote(int studentCod, int oricod, int anocod, int curcod, int matcod, int value) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "UPDATE NOTAS SET NOT_VALUE=? WHERE `NOT_ALUMNOPERCOD`=? AND `NOT_CURORICOD`=? AND `NOT_CURANOCOD`=? AND `NOT_CURCOD`=? AND `NOT_MATCOD`=?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, value);
      preparedStatement.execute();

      return true;
    } catch (SQLException e) {
      System.out.println("ERROR: " + e);
      return false;
    } finally {
      if(connection != null) try {
          connection.close();
      } catch (SQLException ex) {
          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public static float getFinalNote(int studentCod, int oricod, int anocod, int curcod) {
    ArrayList subjects = Subject.getSubjectsCodByCourse(oricod, anocod, curcod);
    
    int finalNotesCount = 0;
    int finalNoteAcum = 0;
    
    for(int i = 0; i < finalNotesCount; i++){ 
      ArrayList notes = getNote(studentCod, oricod, anocod, curcod, (int) subjects.get(i));
      
      if(notes == null) continue;
      
      int notesSize = notes.size();
      int noteAcum = 0;
      
      for(int j = 0; j < notesSize; j++) {
        noteAcum += (int) notes.get(j);
      }
      
      finalNoteAcum += noteAcum/notesSize;
      finalNotesCount++;
    }
    
    return finalNotesCount == 0 ? 0 : (finalNoteAcum/finalNotesCount);
  }
}
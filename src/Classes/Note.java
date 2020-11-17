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
  
  public static boolean createNote(int studentCod, int oricod, int anocod, int curcod, int matcod, int prdcod, int order, int value, int teacherCod) throws SQLException, ParseException {
    ArrayList notes = new ArrayList();
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "INSERT INTO `NOTAS` (`NOT_ALUMNOFECHAINICIO`, `NOT_ALUMNOPERCOD`, `NOT_ALUMNOCATCOD`, `NOT_CURORICOD`, `NOT_CURANOCOD`, `NOT_CURCOD`, `NOT_MATCOD`, `NOT_FORCOD`, `NOT_PRDCOD`, `NOT_ORDEN`, `NOT_ANOFECHA`, `NOT_VALOR`, `NOT_PROFCOD`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, (java.sql.Date) User.getCategorieStartedDateByUsercod(studentCod, Categorie.getCatcod("Alumno")));
      preparedStatement.setInt(2, studentCod);
      preparedStatement.setInt(3, Categorie.getCatcod("Alumno"));
      preparedStatement.setInt(4, oricod);
      preparedStatement.setInt(5, anocod);
      preparedStatement.setInt(6, curcod);
      preparedStatement.setInt(7, matcod);
      preparedStatement.setInt(8, Subject.getForCod(matcod));
      preparedStatement.setInt(9, prdcod);
      preparedStatement.setInt(10, order);
      preparedStatement.setInt(11, java.time.Year.now().getValue());
      preparedStatement.setInt(12, value);
      preparedStatement.setInt(13, teacherCod);

      preparedStatement.execute();
      
      if (prdcod != Period.getPeriodCod("PRIMER TRIMESTRE", false) && prdcod != Period.getPeriodCod("SEGUNDO TRIMESTRE", false)) {
        checkNewPendingNote(studentCod, oricod, anocod, curcod, matcod, prdcod, order, teacherCod);
      }

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
  
  public static int getNote(int studentCod, int oricod, int anocod, int curcod, int matcod, int prdcod, int order){
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    String query = "SELECT * FROM `NOTAS` WHERE `NOT_ALUMNOPERCOD`=? AND `NOT_CURORICOD`=? AND `NOT_CURANOCOD`=? AND `NOT_CURCOD`=? AND `NOT_MATCOD`=?  AND `NOT_PRDCOD`=? AND `NOT_ORDEN`=?;";
    
    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, studentCod);
      preparedStatement.setInt(2, oricod);
      preparedStatement.setInt(3, anocod);
      preparedStatement.setInt(4, curcod);
      preparedStatement.setInt(5, matcod);
      preparedStatement.setInt(6, prdcod);
      preparedStatement.setInt(7, order);
      
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();
      
      boolean isThirdPeriod = false;
      
      if(rs.next()){
        return (int) rs.getInt("NOT_VALOR");
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
  
  public static boolean updateNote(int studentCod, int oricod, int yearcod, int curcod, int matcod, int prdcod, int order, int value, int teacherCod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "UPDATE NOTAS SET NOT_VALOR=? WHERE `NOT_ALUMNOPERCOD`=? AND `NOT_CURORICOD`=? AND `NOT_CURANOCOD`=? AND `NOT_CURCOD`=? AND `NOT_MATCOD`=? AND `NOT_PRDCOD`=? AND `NOT_ORDEN`=?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, value);
      preparedStatement.setInt(2, studentCod);
      preparedStatement.setInt(3, oricod);
      preparedStatement.setInt(4, yearcod);
      preparedStatement.setInt(5, curcod);
      preparedStatement.setInt(6, matcod);
      preparedStatement.setInt(7, prdcod);
      preparedStatement.setInt(8, order);
      preparedStatement.execute();
      
      checkNewPendingNote(studentCod, oricod, yearcod, curcod, matcod, prdcod, order, teacherCod);
      
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
  
  public static float getFinalSubjectNote(int studentCod, int oricod, int yearcod, int curcod, int subcod) {
    float acc = 0;
    float c = 0;
    
    ArrayList periods = Period.getPeriodsCods();
    int periodsSize = periods.size();
    
    int thirdNote = (int) getNote(studentCod, oricod, yearcod, curcod, subcod, Period.getPeriodCod("TERCER TRIMESTRE", false), 4);
    if(thirdNote == 0) return 0;
    
    for (int i = 0; i < periodsSize; i++) {

      int finalNote = 0;
      
      if(!Period.getPeriodName((int) periods.get((int) i)).equals("PRIMER TRIMESTRE") && !Period.getPeriodName((int) periods.get(i)).equals("SEGUNDO TRIMESTRE") && !Period.getPeriodName((int) periods.get(i)).equals("TERCER TRIMESTRE")) continue;
      
      if((int) periods.get(i) == Period.getPeriodCod("TERCER TRIMESTRE", false)) {
        finalNote = thirdNote;
      } else {
        finalNote = getNote(studentCod, oricod, yearcod, curcod, subcod, (int) periods.get(i), 3);
      }
          
      if(finalNote == 0) continue;
      
      acc += (float) finalNote;
      c++;
    }
    
    return c == 0 ? 0 : (Float) (acc/c);
  }
  
  public static float getFinalNote(int studentCod, int oricod, int yearcod, int curcod) {
    float finalNoteCount = 0;
    float finalNoteAcum = 0;
    
    ArrayList subjects = Subject.getSubjectsCodByCourse(oricod, yearcod, curcod);
    int subjectsSize = subjects.size();
    
    for (int i = 0; i < subjectsSize; i++) {
      float finalSubjectNote = (float) getFinalSubjectNote(studentCod, oricod, yearcod, curcod, (int) subjects.get(i));
      if(finalSubjectNote != 0) {
        finalNoteAcum += finalSubjectNote;
        finalNoteCount++;
      }
    }

    return finalNoteCount == 0 ? 0 : (finalNoteAcum/finalNoteCount);
  }  
  
  private static void checkNewPendingNote(int studentCod, int oricod, int yearcod, int curcod, int matcod, int prdcod, int order, int teacherCod) throws ParseException {
  
    if(prdcod == Period.getPeriodCod("TERCER TRIMESTRE", false) && order == 4) {
      int note = (int) getFinalSubjectNote(studentCod, oricod, yearcod, curcod, matcod);

      if(note < 7) {
        PendingNote.newPendingNote(studentCod, oricod, yearcod, curcod, matcod, teacherCod, prdcod);
      } else {
        PendingNote.removePendingNote(studentCod, oricod, yearcod, curcod, matcod, teacherCod);
      }
    } 

    if (prdcod != Period.getPeriodCod("TERCER TRIMESTRE", false) && order == 3) {
      int note = getNote(studentCod, oricod, yearcod, curcod, matcod, prdcod, order);

      if(note < 4) {
        PendingNote.newPendingNote(studentCod, oricod, yearcod, curcod, matcod, teacherCod, prdcod);
      } else {
        PendingNote.removePendingNote(studentCod, oricod, yearcod, curcod, matcod, teacherCod);
      }
    } 

  }
}
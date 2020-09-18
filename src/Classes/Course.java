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

public class Course {
  private int oricod;
  private int anocod;
  private String division;
  public int errorCode;

  public int getOricod() {
      return oricod;
  }

  public void setOricod(int oricod) {
      this.oricod = oricod;
  }

  public int getAnocod() {
      return anocod;
  }

  public void setAnocod(int anocod) {
      this.anocod = anocod;
  }

  public String getDivision() {
      return division;
  }

  public void setDivision(String division) {
      this.division = division;
  }

  public boolean save() throws ParseException {

    if(courseAlreadyCreated(getOricod(), getAnocod(), getDivision())) return true;
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

    String query = "INSERT INTO `CURSOS` (`CUR_ORICOD`, `CUR_ANOCOD`, `CUR_DIVISION`, `CUR_FECHAINICIO`, `CUR_FECHAFIN`) VALUES (?, ?, ?, ?, ?);";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setInt(1, getOricod());
      preparedStatemnet.setInt(2, getAnocod());
      preparedStatemnet.setString(3, getDivision());
      preparedStatemnet.setDate(4, (java.sql.Date) todayDate);
      preparedStatemnet.setDate(5, (java.sql.Date) neverDate);
      preparedStatemnet.execute();


      return true;
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
      errorCode = e.getErrorCode();
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    return false; 
  }

  public static int getCourseCod(String year, String orientation) {

    int yearcode = Year.getYearcod(year);
    int orientationcode = Orientation.getOrientationcod(orientation);

    if(!(yearcode == 0) && !(orientationcode == 0)) {
      BasicDataSource bs = Config.setDBParams();
      Connection connection = null;
      java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
      String query = "SELECT * FROM `CURSOS` WHERE `CUR_ORICOD`='" + orientationcode + "' AND `CUR_ANOCOD`='" + yearcode + "' AND `CUR_FECHAFIN` > ?;";

      try {
        connection = bs.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, todayDate);
        preparedStatement.execute();
        ResultSet rs = (ResultSet) preparedStatement.getResultSet();

        if(rs.next()){
          return (int) rs.getInt("CUR_COD");
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
    }

    return 0;
  }

  public static ArrayList getCoursesCod(String year, String orientation) {

    ArrayList codes = new ArrayList();

    int yearcode = Year.getYearcod(year);
    int orientationcode = Orientation.getOrientationcod(orientation);

    if(!(yearcode == 0) && !(orientationcode == 0)) {
      BasicDataSource bs = Config.setDBParams();
      Connection connection = null;
      java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
      String query = "SELECT * FROM `CURSOS` WHERE `CUR_ORICOD`='" + orientationcode + "' AND `CUR_ANOCOD`='" + yearcode +  "' AND `CUR_FECHAFIN` > ?;";

      try {
        connection = bs.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, todayDate);
        preparedStatement.execute();
        ResultSet rs = (ResultSet) preparedStatement.getResultSet();

        while(rs.next()){
          codes.add(rs.getInt("CUR_COD"));
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
    }

    return codes;
  }

  public static boolean setCourses(int oricod, int anocod, ArrayList curcods, int matcod, int forcod) throws ParseException {
    for (int i = 0; i < curcods.size(); i++) {
        if(!setCourse(oricod, anocod, (int) curcods.get(i), matcod, forcod)) return false;
    }

    return true;
  }

  public static boolean setCourse(int oricod, int anocod, int curcod, int matcod, int forcod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

    String query = "INSERT INTO `CURSOS_MATERIAS` (`CURMAT_ORICOD`, `CURMAT_ANOCOD`, `CURMAT_CURCOD`, `CURMAT_MATCOD`, `CURMAT_FORCOD`, `CURMAT_FECHAFIN`) VALUES (?, ?, ?, ?, ?, ?);";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setInt(1, oricod);
      preparedStatemnet.setInt(2, anocod);
      preparedStatemnet.setInt(3, curcod);
      preparedStatemnet.setInt(4, matcod);
      preparedStatemnet.setInt(5, forcod);
      preparedStatemnet.setDate(6, (java.sql.Date) neverDate);
      preparedStatemnet.execute();


      return true;
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    return false;
  }

  public static boolean courseAlreadyCreated(int oricod, int anocod, String division) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    String query = "SELECT * FROM `CURSOS` WHERE `CUR_ORICOD`=? AND `CUR_ANOCOD`=? AND `CUR_DIVISION`=?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, oricod);
      preparedStatement.setInt(2, anocod);
      preparedStatement.setString(3, division);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      if(rs.next()){
        renovateCourse(rs.getInt("CUR_COD"));
        
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

  public static void renovateCourse(int curcod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

    String query = "UPDATE CURSOS SET CUR_FECHAFIN=? WHERE CUR_COD=" + curcod + " AND CUR_FECHAFIN < ?;";

    try {           
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, neverDate);
      preparedStatemnet.setDate(2, neverDate);
      preparedStatemnet.execute();

    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}

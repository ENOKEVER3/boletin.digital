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
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;

public class Course {

  private int oricod;
  private int anocod;
  private String division;
  public int errorCode;
  public static String errorMessage;

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
  
  public static int getCurcodByDivsion(int oricod, int anocod, String division) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "SELECT * FROM `CURSOS` WHERE `CUR_ORICOD`='" + oricod + "' AND `CUR_ANOCOD`='" + anocod + "' AND `CUR_DIVISION`='" + division + "' AND `CUR_FECHAFIN` > ?;";

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

    return 0;
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

  public static int getCourseCod(String year, String orientation, String division) {

    int yearcode = Year.getYearcod(year);
    int orientationcode = Orientation.getOrientationcod(orientation);
    int divcod = Division.getDivcod(division);
    
    if(!(yearcode == 0) && !(orientationcode == 0)) {
      BasicDataSource bs = Config.setDBParams();
      Connection connection = null;
      java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
      
      String query = "SELECT * FROM `CURSOS` WHERE `CUR_ORICOD`='" + orientationcode + "' AND `CUR_ANOCOD`='" + yearcode + "' AND `CUR_FECHAFIN` > ?;";
      
      if(divcod!=0) {
        query = "SELECT * FROM `CURSOS` WHERE `CUR_ORICOD`='" + orientationcode + "' AND `CUR_ANOCOD`='" + yearcode + "' AND `CUR_DIVCOD`='" + divcod + "' AND `CUR_FECHAFIN` > ?;";
      } 
      
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

  public static boolean setCourses(int oricod, int anocod, ArrayList curcods, int matcod, int forcod) throws ParseException, SQLException {
    for (int i = 0; i < curcods.size(); i++) {
        if(!setCourse(oricod, anocod, (int) curcods.get(i), matcod, forcod)) return false;
    }

    return true;
  }

  public static boolean setCourse(int oricod, int anocod, int curcod, int matcod, int forcod) throws ParseException, SQLException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    if(coursesubjectAlready(oricod, anocod, curcod, matcod, forcod)) {
      renvateCoursesubject(oricod, anocod, curcod, matcod, forcod);
    }
    
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
    String query = "SELECT * FROM `CURSOS` WHERE `CUR_ORICOD`=? AND `CUR_ANOCOD`=? AND `CUR_DIVISION`=? AND `CUR_FECHAFIN` < ?;";
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, oricod);
      preparedStatement.setInt(2, anocod);
      preparedStatement.setString(3, division);
      preparedStatement.setDate(4, neverDate);
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
  
  public static void removeAllStudentsCourses(int percod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "UPDATE `CURSOSMATERIAS_ALUMNOS` SET CURSMATALU_FECHAFIN=? WHERE `CURMATALU_ALUMNOPERCOD`=?";

    try {                                                          
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, neverDate);
      preparedStatemnet.setInt(2, percod);
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
  
  public static boolean registerStudentInCourse(int percod, int oricod, int anocod, int curcod) throws SQLException, ParseException {
    removeAllStudentsCourses(percod);
    
    int catcod = Categorie.getCatcod("Alumno");
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    String query = "SELECT * FROM `PERSONAS_CATEGORIAS` WHERE `PERCAT_CATCOD`=? AND `PERCAT_PERCOD`=? AND `PERCAT_FECHAFIN` > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, catcod);
      preparedStatement.setInt(2, percod);
      preparedStatement.setDate(3, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      if(rs.next()){
        Date startDate = rs.getDate("PERCAT_FECHAINICIO");
        
        if(courseExists(oricod, anocod, curcod)) {
          Connection connection2 = null;
          String query2 = "SELECT * FROM `CURSOS_MATERIAS` WHERE `CURMAT_ORICOD`=? AND `CURMAT_ANOCOD`=? AND `CURMAT_CURCOD`=? AND `CURMAT_FECHAFIN` > ?;";

          try {
            connection2 = bs.getConnection();
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setInt(1, oricod);
            preparedStatement2.setInt(2, anocod);
            preparedStatement2.setInt(3, curcod);
            preparedStatement2.setDate(4, todayDate);
            preparedStatement2.execute();
            ResultSet rs2 = (ResultSet) preparedStatement2.getResultSet();

            while(rs2.next()){
              int matcod = rs2.getInt("CURMAT_MATCOD");
              int forcod = rs2.getInt("CURMAT_FORCOD");
              
              if(!registerStudentInSubject(catcod, percod, startDate, oricod, anocod, curcod, matcod, forcod)) {
                JOptionPane.showMessageDialog(null, "Hubo un error al asignar la matería con código: " + matcod);
              }
            }
            
            return true;
          } catch(SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
          } finally {
            if(connection2 != null) try {
                connection2.close();
              } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        } else {
          errorMessage = "El curso ingresado no existe";
        }
        
        return true;
      } else {
        errorMessage = "El usuario ingresado no es un alumno";
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
  
  private static boolean courseExists(int oricod, int anocod, int curcod) {
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    String query = "SELECT * FROM `CURSOS` WHERE `CUR_ORICOD`=? AND `CUR_ANOCOD`=? AND `CUR_COD`=? AND `CUR_FECHAFIN`>?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, oricod);
      preparedStatement.setInt(2, anocod);
      preparedStatement.setInt(3, curcod);
      preparedStatement.setDate(4, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      if(rs.next()){
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

  private static void updateStudentInSubject(int catcod, int percod, Date startDate, int oricod, int anocod, int curcod, int matcod, int forcod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
                                                                                                                  
    String query = "UPDATE `CURSOSMATERIAS_ALUMNOS` SET CURMATALU_FECHAFIN=? WHERE `CURMATALU_ALUMNOCATCOD`=? AND `CURMATALU_ALUMNOPERCOD`=? AND `CURMATALU_ALUMNOFECHAINICIO`=? AND `CURMATALU_CURORICOD`=? AND `CURMATALU_CURANOCOD`=? AND `CURMATALU_CURCOD`=? AND `CURMATALU_MATCOD`=? AND `CURMATALU_FORCOD`=? AND `CURMATALU_FECHAFIN`<?;";

    try {                                                          
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, neverDate);
      preparedStatemnet.setInt(2, catcod);
      preparedStatemnet.setInt(3, percod);
      preparedStatemnet.setDate(4, (java.sql.Date) startDate);
      preparedStatemnet.setInt(5, oricod);
      preparedStatemnet.setInt(6, anocod);
      preparedStatemnet.setInt(7, curcod);
      preparedStatemnet.setInt(8, matcod);
      preparedStatemnet.setInt(9, forcod);
      preparedStatemnet.setDate(10, neverDate);
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

  private static boolean checkAlreadyRegisteredInSubject(int catcod, int percod, Date startDate, int oricod, int anocod, int curcod, int matcod, int forcod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `CURSOSMATERIAS_ALUMNOS` WHERE `CURMATALU_ALUMNOCATCOD`=? AND `CURMATALU_ALUMNOPERCOD`=? AND `CURMATALU_ALUMNOFECHAINICIO`=? AND `CURMATALU_CURORICOD`=? AND `CURMATALU_CURANOCOD`=? AND `CURMATALU_CURCOD`=? AND `CURMATALU_MATCOD`=? AND `CURMATALU_FORCOD`=? AND `CURMATALU_FECHAFIN`<?;";

    try {                                                          
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setInt(1, catcod);
      preparedStatemnet.setInt(2, percod);
      preparedStatemnet.setDate(3, (java.sql.Date) startDate);
      preparedStatemnet.setInt(4, oricod);
      preparedStatemnet.setInt(5, anocod);
      preparedStatemnet.setInt(6, curcod);
      preparedStatemnet.setInt(7, matcod);
      preparedStatemnet.setInt(8, forcod);
      preparedStatemnet.setDate(9, neverDate);
      preparedStatemnet.execute();

      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();
      
      if(rs.next()) {
        updateStudentInSubject(catcod, percod, startDate, oricod, anocod, curcod, matcod, forcod);
        return true;
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
    
    return false;
  }
  
  private static boolean registerStudentInSubject(int catcod, int percod, Date startDate, int oricod, int anocod, int curcod, int matcod, int forcod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    if(checkAlreadyRegisteredInSubject(catcod, percod, startDate, oricod, anocod, curcod, matcod, forcod)) return true;
    
    String query = "INSERT INTO `CURSOSMATERIAS_ALUMNOS` (`CURMATALU_ALUMNOCATCOD`, `CURMATALU_ALUMNOPERCOD`, `CURMATALU_ALUMNOFECHAINICIO`, `CURMATALU_CURORICOD`, `CURMATALU_CURANOCOD`, `CURMATALU_CURCOD`, `CURMATALU_MATCOD`, `CURMATALU_FORCOD`, `CURMATALU_FECHAFIN`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    try {                                                          
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setInt(1, catcod);
      preparedStatemnet.setInt(2, percod);
      preparedStatemnet.setDate(3, (java.sql.Date) startDate);
      preparedStatemnet.setInt(4, oricod);
      preparedStatemnet.setInt(5, anocod);
      preparedStatemnet.setInt(6, curcod);
      preparedStatemnet.setInt(7, matcod);
      preparedStatemnet.setInt(8, forcod);
      preparedStatemnet.setDate(9, neverDate);
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
  
  private static boolean coursesubjectAlready(int oricod, int anocod, int curcod, int matcod, int forcod) throws ParseException, SQLException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

    String query = "SELECT * FROM `CURSOS_MATERIAS` WHERE `CURMAT_ORICOD`=? AND `CURMAT_ANOCOD`=? AND `CURMAT_CURCOD`=? AND `CURMAT_MATCOD`=? AND `CURMAT_FORCOD`=? AND `CURMAT_FECHAFIN`<?;";

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
      
      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();
      
      if(rs.next()) {
        return true;
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
    
    return false;
  }

  private static void renvateCoursesubject(int oricod, int anocod, int curcod, int matcod, int forcod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

    String query = "UPDATE CURSOS_MATERIAS SET CUR_FECHAFIN=? WHERE `CURMAT_ORICOD`=? AND `CURMAT_ANOCOD`=? AND `CURMAT_CURCOD`=? AND `CURMAT_MATCOD`=? AND `CURMAT_FORCOD`=?";

    try {           
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, neverDate);
       preparedStatemnet.setInt(2, oricod);
      preparedStatemnet.setInt(3, anocod);
      preparedStatemnet.setInt(4, curcod);
      preparedStatemnet.setInt(5, matcod);
      preparedStatemnet.setInt(6, forcod);
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
  
  public static ArrayList getStudentsByCourse(int curcod) throws ParseException {
    ArrayList students = new ArrayList();
    
    int matcod = Subject.getFirstSubjectByCourse(curcod);
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `CURSOSMATERIAS_ALUMNOS` WHERE `CURMATALU_CURCOD`=? AND `CURMATALU_MATCOD`=?  AND `CURMATALU_FECHAFIN`<?;";

    try {                                                          
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setInt(1, curcod);
      preparedStatemnet.setInt(2, matcod);
      preparedStatemnet.setDate(3, neverDate);
      preparedStatemnet.execute();

      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();
      
      while(rs.next()) {
        User student = User.getUser(rs.getInt("CURMATALU_ALUMNOPERCOD"));
        if (!students.contains(student)) students.add(student);
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
   
    return students;
  }
}

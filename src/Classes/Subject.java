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

public class Subject {
  private static int forcod;
  private static String name;
  public static int errorCode;

  public static int getForcod() {
    return forcod;
  }

  public static void setForcod(int forcod) {
    Subject.forcod = forcod;
  }

  public static String getName() {
    return name;
  }

  public static void setName(String name) {
    Subject.name = name;
  }

  public static int getSubjectcod(String subject, int forcod) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

    String query = "SELECT * FROM `MATERIAS` WHERE `MAT_NOMBRE`='" + subject + "' AND `MAT_FORCOD`='" + forcod + "' AND 'MAT_FECHAFIN' > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, todayDate);
      preparedStatemnet.execute();
      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();

      if(rs.next()){
        return (int) rs.getInt("MAT_COD");
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
    
  public static boolean checkType(String type) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

    String query = "SELECT * FROM `FORMACIONES` WHERE `FOR_NOMBRE`='" + type + "' AND 'FOR_FECHAFIN' > ?;";

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
    
  public static boolean save() throws ParseException {
    if(checkAlreadyCreatedSubject(getName(), getForcod())) return true;

    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

    String query = "INSERT INTO `MATERIAS` (`MAT_FORCOD`, `MAT_NOMBRE`, `MAT_FECHAFIN`) VALUES (?, ?, ?);";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setInt(1, getForcod());
      preparedStatemnet.setString(2, getName());
      preparedStatemnet.setDate(3, neverDate);
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
    
  public static void delete() {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "UPDATE MATERIAS SET MAT_FECHAFIN=? WHERE MAT_COD=" + getSubjectcod(getName(), getForcod()) + " AND MAT_FORCOD=" + getForcod() + ";";

    try {
      java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, todayDate);
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

  private static boolean checkAlreadyCreatedSubject(String name, int forcod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());
    
    String query = "SELECT * FROM `MATERIAS` WHERE `MAT_NOMBRE`='" + name + "' AND 'MAT_FORCOD'=" + forcod +";";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.execute();
      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();

      if(rs.next()){
        renovateSubject(rs.getInt("MAT_COD"), rs.getInt("MAT_FORCOD"));
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

  private static void renovateSubject(int matcod, int forcod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

    String query = "UPDATE MATERIAS SET MAT_FECHAFIN=? WHERE MAT_COD=" + matcod + " AND MAT_FORCOD=" + forcod + ";";

    try {           
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, neverDate);
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
  
  public static ArrayList getSubjects() {
    ArrayList subjects = new ArrayList(); 
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "SELECT * FROM `MATERIAS` WHERE `MAT_FECHAFIN` > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      while(rs.next()){
        String subject = rs.getString("MAT_NOMBRE");
        if(!subjects.contains(subject)) subjects.add(subject);
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
    
    return subjects; 
  }
  
  public static int getForCod(int matcod, String subjectName) {
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "SELECT * FROM `MATERIAS` WHERE MAT_COD='"+ matcod + "' AND MAT_NOMBRE='" + subjectName + "' AND `MAT_FECHAFIN` > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      while(rs.next()){
        return rs.getInt("MAT_FORCOD");
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
  
  public static ArrayList getTypes() {
    ArrayList types = new ArrayList(); 
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "SELECT * FROM `FORMACIONES` WHERE `FOR_FECHAFIN` > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      while(rs.next()){
        String type = rs.getString("FOR_NOMBRE");
        if(!types.contains(type)) types.add(type);
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
    
    return types; 
  }
  
  public static int getTypeCodeByName(String type) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    String query = "SELECT * FROM `FORMACIONES` WHERE `FOR_FECHAFIN` > ? AND `FOR_NOMBRE`='" + type +"';";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      if(rs.next()){
        return (int) rs.getInt("FOR_COD");
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
  
  
  public static ArrayList getSubjectsCodByCourse(int oricod, int anocod, int curcod) {
    ArrayList cods = new ArrayList();
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `CURSOS_MATERIAS` WHERE `CURMAT_ORICOD`=? AND `CURMAT_ANOCOD`=? AND `CURMAT_CURCOD`=? AND `CURMAT_FECHAFIN` > ?;";
    
      try {
        connection = bs.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, oricod);
        preparedStatement.setInt(2, anocod);
        preparedStatement.setInt(3, curcod);
        preparedStatement.setDate(4, todayDate);
        preparedStatement.execute();
        ResultSet rs = (ResultSet) preparedStatement.getResultSet();

        while(rs.next()){
          int matcod = rs.getInt("CURMAT_MATCOD");
          if(!cods.contains(matcod)) cods.add(matcod);
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
    
    return cods;
  }
  
  
  // two first items of the arraylist are the matcod and the forcod for optization reasons
  public static ArrayList getTeachersCod(int oricod, int anocod, int curcod, String subject) {
    ArrayList teacherCods = new ArrayList();
    
    ArrayList subjectCods = getSubjectsCodByCourse(oricod, anocod, curcod);
    
    if(subjectCods.size() <= 0) {
      JOptionPane.showMessageDialog(null, "No se ha encontrado la materia");
      return null;
    }
    
    subjectCods.forEach(matcod -> {
      int forcod = getForCod((int) matcod, subject);

      if(forcod != 0) {
        java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

        BasicDataSource bs = Config.setDBParams();
        Connection connection = null;

        String query = "SELECT * FROM `CURSOSMATERIAS_PROFESORES` WHERE `CURMATPRO_ORICOD`=? AND `CURMATPRO_ANOCOD`=? AND `CURMATPRO_CURCOD`=? AND `CURMATPRO_MATCOD`=? AND `CURMATPRO_FORCOD`=? AND `CURMATPRO_FECHAFIN` > ?;";

          try {
            connection = bs.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, oricod);
            preparedStatement.setInt(2, anocod);
            preparedStatement.setInt(3, curcod);
            preparedStatement.setInt(4, (int) matcod);
            preparedStatement.setInt(5, forcod);       
            preparedStatement.setDate(6, todayDate);
            preparedStatement.execute();
            ResultSet rs = (ResultSet) preparedStatement.getResultSet();

            teacherCods.add(matcod);
            teacherCods.add(forcod);

            while(rs.next()){
              int teacherCod = rs.getInt("CURMATPRO_PERCOD");
              if(!teacherCods.contains(teacherCod)) teacherCods.add(teacherCod);
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
      } 
    });
    
    return teacherCods;
  }
  
  public static boolean registerTeacherInSubject(int percod, int oricod, int anocod, int curcod, int matcod, int forcod) throws ParseException {
    if(TeacherAlreadyInSubject(percod, oricod, anocod, curcod, matcod, forcod)) return true;
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    int catcod = Categorie.getCatcod("Profesor");
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());
    
    String query = "INSERT INTO `CURSOSMATERIAS_PROFESORES` (`CURMATPRO_FECHAINICIO`, `CURMATPRO_PERCOD`, `CURMATPRO_CATCOD`, `CURMATPRO_ORICOD`, `CURMATPRO_ANOCOD`, `CURMATPRO_CURCOD`, `CURMATPRO_MATCOD`, `CURMATPRO_FORCOD`, `CURMATPRO_ANOFECHA`, `CURMATPRO_FECHAFIN`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, (java.sql.Date) User.getCategorieStartedDateByUsercod(percod, catcod));
      preparedStatement.setInt(2, percod);
      preparedStatement.setInt(3, catcod);
      preparedStatement.setInt(4, oricod);
      preparedStatement.setInt(5, anocod);
      preparedStatement.setInt(6, curcod);
      preparedStatement.setInt(7, matcod);
      preparedStatement.setInt(8, forcod);
      preparedStatement.setInt(9, java.time.Year.now().getValue());
      preparedStatement.setDate(10, neverDate);
 
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
  public static boolean TeacherAlreadyInSubject(int percod, int oricod, int anocod, int curcod, int matcod, int forcod) throws ParseException {
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `CURSOSMATERIAS_PROFESORES` WHERE `CURMATPRO_PERCOD`=? AND `CURMATPRO_ORICOD`=? AND `CURMATPRO_ANOCOD`=? AND `CURMATPRO_CURCOD`=? AND `CURMATPRO_MATCOD`=? AND `CURMATPRO_FORCOD`=?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, percod);
      preparedStatement.setInt(2, oricod);
      preparedStatement.setInt(3, anocod);
      preparedStatement.setInt(4, curcod);
      preparedStatement.setInt(5, (int) matcod);
      preparedStatement.setInt(6, forcod); 
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();
      
      if(rs.next()){
        renovateTeacherInSubject(percod, oricod, anocod, curcod, matcod, forcod);
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
  
  public static void renovateTeacherInSubject(int percod, int oricod, int anocod, int curcod, int matcod, int forcod) throws ParseException {  
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());
    
    String query = "UPDATE `CURSOSMATERIAS_PROFESORES` SET `CURMATPRO_FECHAFIN`=?, `CURMATPRO_ANOFECHA`=? WHERE `CURMATPRO_PERCOD`=? AND `CURMATPRO_ORICOD`=? AND `CURMATPRO_ANOCOD`=? AND `CURMATPRO_CURCOD`=? AND `CURMATPRO_MATCOD`=? AND `CURMATPRO_FORCOD`=?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, neverDate);
      preparedStatement.setInt(2, java.time.Year.now().getValue());    
      preparedStatement.setInt(3, percod);
      preparedStatement.setInt(4, oricod);
      preparedStatement.setInt(5, anocod);
      preparedStatement.setInt(6, curcod);
      preparedStatement.setInt(7, matcod);
      preparedStatement.setInt(8, forcod);      
      
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
  
  
  public static void deleteSubjectTeacher(int percod, int oricod, int anocod, int curcod, int matcod, int forcod) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
     
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
     
    String query = "UPDATE `CURSOSMATERIAS_PROFESORES` SET `CURMATPRO_FECHAFIN`=? WHERE `CURMATPRO_PERCOD`=? AND `CURMATPRO_ORICOD`=? AND `CURMATPRO_ANOCOD`=? AND `CURMATPRO_CURCOD`=? AND `CURMATPRO_MATCOD`=? AND `CURMATPRO_FORCOD`=?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.setInt(2, percod);
      preparedStatement.setInt(3, oricod);
      preparedStatement.setInt(4, anocod);
      preparedStatement.setInt(5, curcod);
      preparedStatement.setInt(6, matcod);
      preparedStatement.setInt(7, forcod);       
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

  public static ArrayList getSubjectsCodByCourseAndCategory(int oricod, int anocod, int typecode) {
    ArrayList cods = new ArrayList();
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `CURSOS_MATERIAS` WHERE `CURMAT_ORICOD`=? AND `CURMAT_ANOCOD`=? AND `CURMAT_FORCOD`=? AND `CURMAT_FECHAFIN` > ?;";
    
      try {
        connection = bs.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, oricod);
        preparedStatement.setInt(2, anocod);
        preparedStatement.setInt(3, typecode);
        preparedStatement.setDate(4, todayDate);
        preparedStatement.execute();
        ResultSet rs = (ResultSet) preparedStatement.getResultSet();

        while(rs.next()){
          int matcod = rs.getInt("CURMAT_MATCOD");
          if(!cods.contains(matcod)) cods.add(matcod);
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
    
    return cods;
  }

  public static String getSubjectNameByCode(int subcod) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

    String query = "SELECT * FROM `MATERIAS` WHERE `MAT_COD`='" + subcod + "' AND 'MAT_FECHAFIN' > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, todayDate);
      preparedStatemnet.execute();
      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();

      if(rs.next()){
        return (String) rs.getString("MAT_NOMBRE");
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

  public static boolean removeSubject(int subcod) throws ParseException {
    if(!removeSubjectCourses(subcod)) return false;
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

    String query = "UPDATE MATERIAS SET MAT_FECHAFIN=? WHERE MAT_COD=" + subcod + ";";

    try {           
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, todayDate);
      preparedStatemnet.execute();
      
      return true;
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
      return false;
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public static boolean changeSubjectName(int subcod, String name) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

    String query = "UPDATE MATERIAS SET MAT_NOMBRE=? WHERE MAT_COD=" + subcod + " AND MAT_FECHAFIN > ?;";

    try {           
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setString(1, name);
      preparedStatemnet.setDate(2, todayDate);
      preparedStatemnet.execute();
      
      return true;
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
      return false;
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  private static boolean removeSubjectCourses(int subcod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

    String query = "UPDATE CURSOS_MATERIAS SET CURMAT_FECHAFIN=? WHERE CURMAT_MATCOD=" + subcod + ";";

    try {           
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setDate(1, todayDate);
      preparedStatemnet.execute();
      
      return true;
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
      return false;
    } finally {
      if(connection != null) try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  static int getFirstSubjectByCourse(int curcod) {
    ArrayList cods = new ArrayList();
    
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `CURSOS_MATERIAS` WHERE `CURMAT_CURCOD`=? AND `CURMAT_FECHAFIN` > ?;";
    
      try {
        connection = bs.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, curcod);
        preparedStatement.setDate(2, todayDate);
        preparedStatement.execute();
        ResultSet rs = (ResultSet) preparedStatement.getResultSet();

        if(rs.next()){
          return rs.getInt("CURMAT_MATCOD");
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
    
    return 0;
  }
  
}

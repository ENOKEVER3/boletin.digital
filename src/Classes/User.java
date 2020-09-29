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
public class User {  

  private String username;
  private String name;
  private String lastname;
  private String email;
  private String optionalEmail;
  private String password;
  private long phone;
  private String gender;
  private int errorCode;
  
  
  public String getUsername() {
      return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getOptionalEmail() {
    return optionalEmail;
  }

  public void setOptionalEmail(String optionalEmail) {
    this.optionalEmail = optionalEmail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public long getPhone() {
    return phone;
  }

  public void setPhone(long phone) {
    this.phone = phone;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int codeError) {
    this.errorCode = codeError;
  }

  public static boolean checkUsername(String username) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "SELECT * FROM `PERSONAS` WHERE `PER_USUARIO`='" + username + "'";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
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

  public boolean save() {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "INSERT INTO `PERSONAS` (`PER_COD`, `PER_USUARIO`, `PER_NOMBRE`, `PER_APELLIDO`, `PER_CONTRASENA`, `PER_CORREO`, `PER_CORREO2`, `PER_TELEFONO`, `PER_SEXO`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?);";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setString(1, getUsername());
      preparedStatemnet.setString(2, getName());
      preparedStatemnet.setString(3, getLastname());
      preparedStatemnet.setString(4, getPassword());
      preparedStatemnet.setString(5, getEmail());
      preparedStatemnet.setString(6, getOptionalEmail());
      preparedStatemnet.setLong(7, getPhone());
      preparedStatemnet.setString(8, getGender());
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

  public static void delete(int usercod) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "UPDATE PERSONAS SET PER_FECHAFIN=? WHERE PER_COD=" + usercod + ";";

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
  
  public boolean modify() {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "UPDATE PERSONAS SET PER_NOMBRE=?,PER_APELLIDO=?,PER_CONTRASENA=?,PER_CORREO=?,PER_CORREO2=?,PER_TELEFONO=?,PER_SEXO=? WHERE `PER_COD`=" + String.valueOf(getUserCodeByUsername(getUsername())) + ";";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, getName());
      preparedStatement.setString(2, getLastname());
      preparedStatement.setString(3, getPassword());
      preparedStatement.setString(4, getEmail());
      preparedStatement.setString(5, getOptionalEmail());
      preparedStatement.setLong(6, getPhone());
      preparedStatement.setString(7, getGender());
      preparedStatement.execute();


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

  public static void addCategorie(String categorie, String username) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());
    
    int percod = getUserCodeByUsername(username);
    int catcod = getCatcodByCategorieName(categorie);

    if(checkPreviusCategorie(catcod, percod)) {
      updateCategorie(catcod, percod);
      return;
    }

    String query = "INSERT INTO `PERSONAS_CATEGORIAS` (`PERCAT_CATCOD`, `PERCAT_PERCOD`, `PERCAT_FECHAINICIO`, `PERCAT_FECHAFIN`) VALUES (?, ?, ?, ?);";

    try {                                                          
      java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.setInt(1, catcod);
      preparedStatemnet.setInt(2, percod);
      preparedStatemnet.setDate(3, todayDate);
      preparedStatemnet.setDate(4, neverDate);
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

  public static void removeCategorie(String categorie, String username) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    int catcod = getCatcodByCategorieName(categorie);
    int percod = getUserCodeByUsername(username);

    String query = "UPDATE PERSONAS_CATEGORIAS SET PERCAT_FECHAFIN=? WHERE PERCAT_CATCOD=" + catcod + " AND PERCAT_PERCOD=" + getUserCodeByUsername(username) + ";";

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

  static public int getCatcodByCategorieName(String categorie) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "SELECT * FROM `CATEGORIAS` WHERE `CAT_NOMBRE`='" + categorie + "'";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.execute();
      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();

      if(rs.next()){
        return (int) rs.getInt("CAT_COD");
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

  static public int getUserCodeByUsername(String username) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "SELECT * FROM `PERSONAS` WHERE `PER_USUARIO`='" + username + "'";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatemnet = connection.prepareStatement(query);
      preparedStatemnet.execute();
      ResultSet rs = (ResultSet) preparedStatemnet.getResultSet();

      if(rs.next()){
        int percod = rs.getInt("PER_COD");
        return percod;
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

  public static ArrayList getUserCategoriesByUsercode(int usercode) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    ArrayList userCategories = new ArrayList();
    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

    String query = "SELECT * FROM `PERSONAS_CATEGORIAS` WHERE `PERCAT_PERCOD`='" + usercode + "' AND `PERCAT_FECHAFIN` > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      while(rs.next()){
        userCategories.add(rs.getInt("PERCAT_CATCOD"));
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

    return userCategories;
  }

  public static ArrayList searchUsersUsername(String name, String lastname, int catcod) {
    ArrayList usernames = new ArrayList();
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `PERSONAS` WHERE `PER_NOMBRE` LIKE '%" + name + "%' AND 'PER_APELLIDO' LIKE '%"+ lastname + "%';";
    
    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      while(rs.next()){
        if (catcod == 0) {
          usernames.add(rs.getString("PER_USUARIO"));
        }
        
        ArrayList categories = getUserCategoriesByUsercode(rs.getInt("PER_COD"));
        
        categories.forEach(categorie -> {
          if((int) categorie == catcod) {
            try {
              usernames.add(rs.getString("PER_USUARIO"));
            } catch (SQLException ex) {
              Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
        });
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
    
    return usernames;
  }
  
  private static boolean checkPreviusCategorie(int catcod, int percod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

    String query = "SELECT * FROM `PERSONAS_CATEGORIAS` WHERE `PERCAT_PERCOD`=" + percod + " AND `PERCAT_CATCOD`=" + catcod + ";";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      if(rs.next()) {
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

  public static boolean changePassword(String username, String password) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;


    String query = "UPDATE PERSONAS SET PER_CONTRASENA=? WHERE `PER_USUARIO`='" + username + "';";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, password);
      preparedStatement.execute();

      return true;

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

  public static boolean checkPassword(String username, String password) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `PERSONAS` WHERE PER_USUARIO='" + username + "' AND PER_CONTRASENA='"+ password + "';";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

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

  public static boolean updateCategorie(int catcod, int percod) throws ParseException {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    String query = "UPDATE `PERSONAS_CATEGORIAS` SET `PERCAT_FECHAFIN`=? WHERE `PERCAT_CATCOD`=" + catcod + " AND `PERCAT_PERCOD`=" + percod + ";";

      try {                                                          
        java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        java.sql.Date neverDate = new java.sql.Date(sdf.parse("01-01-3000").getTime());

        connection = bs.getConnection();
        PreparedStatement preparedStatemnet = connection.prepareStatement(query);
        preparedStatemnet.setDate(1, neverDate);
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
  
  public static User getUser(int usercod) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    User user = new User();
    
    String query = "SELECT * FROM `PERSONAS` WHERE PER_COD='" + usercod + "';";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      if(rs.next()){
          user.setUsername(rs.getString("PER_USUARIO"));
          user.setName(rs.getString("PER_NOMBRE"));
          user.setLastname(rs.getString("PER_APELLIDO"));
          user.setEmail(rs.getString("PER_CORREO"));
          user.setOptionalEmail(rs.getString("PER_CORREO2"));
          user.setPhone(rs.getLong("PER_TELEFONO"));
          user.setGender(rs.getString("PER_SEXO"));
          
          return user;
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

    return null;
  }
  
  public static String getFullNameByCod(int usercod) {
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;
    
    String query = "SELECT * FROM `PERSONAS` WHERE PER_COD='" + usercod + "';";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      if(rs.next()){ 
        String name = rs.getString("PER_NOMBRE").substring(0, 1).toUpperCase() + rs.getString("PER_NOMBRE").substring(1).toLowerCase();
        String lastname = rs.getString("PER_APELLIDO").substring(0, 1).toUpperCase() + rs.getString("PER_APELLIDO").substring(1).toLowerCase();
        return (name + " " + lastname);
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
  
  public static ArrayList getUsersFromCategories(String categorie) {
    ArrayList teachers = new ArrayList();
    
    BasicDataSource bs = Config.setDBParams();
    Connection connection = null;

    java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
    
    User user = new User();
    
    int catcod = Categorie.getCatcod(categorie);
    
    String query = "SELECT * FROM `PERSONAS_CATEGORIAS` WHERE PERCAT_CATCOD='" + catcod + "' AND PERCAT_FECHAFIN > ?;";

    try {
      connection = bs.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, todayDate);
      preparedStatement.execute();
      ResultSet rs = (ResultSet) preparedStatement.getResultSet();

      while(rs.next()){
        teachers.add(getUser(rs.getInt("PERCAT_PERCOD")));
      }
      
      return teachers;
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

}

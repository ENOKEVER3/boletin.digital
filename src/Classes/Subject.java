package Classes;

import Forms.Login;
import Utils.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public static boolean checkYear(String year, int orientation) {
        BasicDataSource bs = Config.setDBParams();
        Connection connection = null;
        java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
        
        String query = "SELECT * FROM `ANOS` WHERE `ANO_NOMBRE`='" + year + "' AND `ANO_ORICOD`='" + orientation + "' AND 'ANO_FECHAFIN' > ?;";
        
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

    public static int getSubjectcod(String subject, int forcod) {
        BasicDataSource bs = Config.setDBParams();
        Connection connection = null;
        java.sql.Date todayDate = new java.sql.Date(new Date().getTime());
        
        String query = "SELECT * FROM `MATERIAS` WHERE `MAT_NOMBRE`='" + subject + "' AND `MAT_FORCOD`='" + forcod + "' AND 'ANO_FECHAFIN' > ?;";
        
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
        
        String query = "SELECT * FROM `FORMACIONES` WHERE `FOR_NOMBRE`='" + type + "' AND 'ANO_FECHAFIN' > ?;";
        
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
}

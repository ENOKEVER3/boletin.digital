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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    private String tutor;
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

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
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

        String query = "SELECT * FROM `PERSONAS` WHERE `PER_USUARIO` LIKE '" + username + "'";
        
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

        String query = "INSERT INTO `PERSONAS` (`PER_COD`, `PER_USUARIO`, `PER_NOMBRE`, `PER_APELLIDO`, `PER_CONTRASENA`, `PER_CORREO`, `PER_CORREO2`, `PER_TELEFONO`, `PER_SEXO`, `PER_TUTOR`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
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
            preparedStatemnet.setString(9, getTutor());
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

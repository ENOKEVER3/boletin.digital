package Classes;

import Forms.Login;
import Utils.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

public class Courses {
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
}

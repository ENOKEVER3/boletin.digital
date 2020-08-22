package boletindigital;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    public static String url = "jdbc:mysql://18.231.175.39:3306/boletindigital";
    public static String user = "root";
    public static String pass = "boletin123digital456";
    
    public static BasicDataSource setearParametros(){

        
        BasicDataSource bs = new BasicDataSource();
        bs.setDriverClassName("com.mysql.jdbc.Driver");
        bs.setUsername(user);
        bs.setPassword(pass);
        bs.setUrl(url);
        return bs;
    }
}

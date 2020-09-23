package Utils;

import org.apache.commons.dbcp2.BasicDataSource;

public class Config {

    public static String url = Credentials.url;
    public static String user = Credentials.user;
    public static String pass = Credentials.password;
   
    public static BasicDataSource setDBParams(){

        BasicDataSource bs = new BasicDataSource();
        bs.setDriverClassName("com.mysql.jdbc.Driver");
        bs.setUsername(user);
        bs.setPassword(pass);
        bs.setUrl(url);
        return bs;
    }
}

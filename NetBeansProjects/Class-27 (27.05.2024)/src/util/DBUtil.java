
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Shabab-1281539
 */
public class DBUtil {
    
    private Connection connection;
    private String dbURL = "jdbc:mysql://localhost:3306/jee59store";
    private String username = "root";
    private String password = "1234";
    private String driver = "com.mysql.cj.jdbc.Driver";
    
    public Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbURL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
}

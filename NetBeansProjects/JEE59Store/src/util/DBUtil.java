
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Shabab Ahmed
 */
public class DBUtil {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private Connection connection;
    private String dbURL = "jdbc:mysql://localhost:3306/jee_59_store";
    private String username = "root";
    private String password = ""; //Adjust in TSP

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

package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Shabab-1281539
 */
public class DBUtil {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/db_crud";
    Connection connection;
    String username = "root";
    String password = "";

    public Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}

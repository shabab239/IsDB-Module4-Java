package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Shabab-1281539
 */
public class DBUtil {

    final String url = "jdbc:mysql://localhost:3306/db_crud_emp";
    final String driver = "com.mysql.cj.jdbc.Driver";
    final String username = "root";
    final String password = "";

    private Connection connection;

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

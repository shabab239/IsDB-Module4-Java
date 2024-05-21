
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Shabab-1281539
 */
public class JDBCTest {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");
            
            
            
            PreparedStatement ps = connection.prepareStatement("insert into test.user(username, password) values(\"User03\", \"OMGPassword!\");");
            ps.executeUpdate();
            
            ps = connection.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                System.out.println(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBUtil;

/**
 *
 * @author Shabab-1281539
 */
public class JDBCTest {

    public static void main(String[] args) {

        DBUtil dBUtil = new DBUtil();
        Connection connection = dBUtil.getConnection();
        PreparedStatement ps;
        try {

            ps = connection.prepareStatement("insert into user(name,mobile,email,address) values(?, ?, ?, ?);");
            ps.setString(1, "Shabab");
            ps.setString(2, "01710295968");
            ps.setString(3, "sabab239.1@gmail.com");
            ps.setString(4, "Dhaka");

            ps.executeUpdate();
            ps.close();
            
            ps = connection.prepareStatement("select * from user;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String address = rs.getString("address");
                
                System.out.printf("ID: %s, Name: %s, Mobile: %s, Email: %s, Address: %s\n", id, name, mobile, email, address);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

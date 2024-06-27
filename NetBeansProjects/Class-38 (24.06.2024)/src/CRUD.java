
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;
import util.DBUtil;


/**
 *
 * @author Shabab-1281539
 */
public class CRUD {
    private static DBUtil dbUtil = new DBUtil();
    private static Connection connection;

    public static void main(String[] args) {
        
        User userToCreate = new User("Shabab", "test@test.com", "My Address", "01900000000");
        createUser(userToCreate);
        
        readUser();
        
        User userToUpdate = new User(10L, "Shabab", "test@test.com", "My Address", "01900000000");
        updateUser(userToUpdate);
        
        User userToDelete = new User(12L);
        deleteUser(userToDelete);
        
        
    }
    
    public static void createUser(User user) {
        try {
            connection = dbUtil.getConnection();
            
            String query = "insert into user(name, email, address, cell) values(?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getCell());
            ps.executeUpdate();
            ps.close();
            
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void readUser() {
        try {
            connection = dbUtil.getConnection();
            
            String query = "select * from user;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setCell(rs.getString("cell"));
                System.out.println(user.toString());
            }
            rs.close();
            ps.close();
            
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updateUser(User user) {
        try {
            connection = dbUtil.getConnection();
            
            String query = "update user set name = ?, email = ?, address = ?, cell = ? where id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getCell());
            ps.setLong(5, user.getId());
            ps.executeUpdate();
            ps.close();
            
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteUser(User user) {
        try {
            connection = dbUtil.getConnection();
            
            String query = "delete from user where id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, user.getId());
            ps.executeUpdate();
            ps.close();
            
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

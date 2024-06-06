
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.User;
import util.DBUtil;

/**
 *
 * @author Shabab-1281539
 */
public class CRUDTest {

    static DBUtil dBUtil = new DBUtil();
    static PreparedStatement ps;

    public static void main(String[] args) {

        //Create
        User user = new User();
        user.setName("Skip Khan");
        user.setEmail("lol@lol.com");
        user.setMobile("01700000000");
        user.setAddress("Dhaka");
        saveUser(user);

        //Update
        user = new User();
        user.setId(5L);
        user.setEmail("lol@lol.com");
        editUser(user);

        //Delete
        user = new User();
        user.setId(4L);
        deleteUser(user);

        //Read
        showAllUsers();

    }

    public static void showAllUsers() {
        System.out.println("==============================ALL USERS==============================");
        Connection connection = dBUtil.getConnection();
        try {
            ps = connection.prepareStatement("select * from user;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String address = rs.getString("address");

                System.out.printf("ID: %s, Name: %s, Mobile: %s, Email: %s, Address: %s\n", id, name, mobile, email, address);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveUser(User user) {
        Connection connection = dBUtil.getConnection();
        try {

            ps = connection.prepareStatement("insert into user(name,mobile,email,address) values(?, ?, ?, ?);");

            ps.setString(1, user.getName());
            ps.setString(2, user.getMobile());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress());

            ps.executeUpdate();

            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<User> getUsersById(List<Long> userIdList) {
        List<User> userList = new ArrayList<>();
        Connection connection = dBUtil.getConnection();
        try {
            String sql = String.format("select * from user where id in (%s)",
                    userIdList.stream()
                            .map(v -> "?")
                            .collect(Collectors.joining(", ")));

            ps = connection.prepareStatement(sql);
//            Array array = connection.createArrayOf("BIGINT", userIdArray); //Not Supported in current MySQL version
//            ps.setArray(1, array);
            for (int i = 0; i < userIdList.size(); i++) {
                ps.setLong(i + 1, userIdList.get(i));
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String address = rs.getString("address");

                User user = new User(id, name, mobile, email, address);
                userList.add(user);
            }

            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static void editUser(User user) {
        Connection connection = dBUtil.getConnection();
        try {
            List<User> dbUsers = getUsersById(new ArrayList<>() {
                {
                    add(user.getId());
                }
            });
            if (dbUsers.isEmpty()) {
                System.out.println("No User Found. Could not edit.");
                return;
            }
            User dbUser = dbUsers.get(0);
            ps = connection.prepareStatement("update user set name = ?, mobile = ?, email = ?, address = ? where id = ?;");

            ps.setString(1, user.getName() == null ? dbUser.getName() : user.getName());
            ps.setString(2, user.getMobile() == null ? dbUser.getMobile() : user.getMobile());
            ps.setString(3, user.getEmail() == null ? dbUser.getEmail() : user.getEmail());
            ps.setString(4, user.getAddress() == null ? dbUser.getAddress() : user.getAddress());
            ps.setLong(5, dbUser.getId());

            ps.executeUpdate();

            ps.close();
            connection.close();
            System.out.println("Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(User user) {
        Connection connection = dBUtil.getConnection();
        try {
            List<User> dbUsers = getUsersById(new ArrayList<>() {
                {
                    add(user.getId());
                }
            });
            if (dbUsers.isEmpty()) {
                System.out.println("No User Found. Could not delete.");
                return;
            }
            User dbUser = dbUsers.get(0);
            ps = connection.prepareStatement("delete from user where id = ?;");
            ps.setLong(1, dbUser.getId());

            ps.executeUpdate();

            ps.close();
            connection.close();
            System.out.println("Deleted Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

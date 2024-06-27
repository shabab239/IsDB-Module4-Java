
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Employee;
import util.DBUtil;

/**
 *
 * @author Shabab-1281539
 */
public class CRUD {

    private static DBUtil dBUtil = new DBUtil();
    private static Connection connection;

    public static void main(String[] args) {
        Employee employeeToCreate = new Employee("Shabab", "test@mail.com", "01700000000", "Dhaka");
        createEmployee(employeeToCreate);
        Employee employeeToCreate2 = new Employee("Samin", "example@mail.com", "01900000000", "Chittagong");
        createEmployee(employeeToCreate2);

        System.out.println("==============AFTER INSERT==============");
        readEmployees();

        Employee employeeToUpdate = new Employee(6L, "Shabab 2", "test@mail.com", "01900000000", "Dhaka");
        updateEmployee(employeeToUpdate);

        System.out.println("==============AFTER UPDATE==============");
        readEmployees();

        Employee employeeToDelete = new Employee(7L);
        deleteEmployee(employeeToDelete);
        
        System.out.println("==============AFTER DELETE==============");
        readEmployees();
    }

    public static void createEmployee(Employee employee) {
        try {
            connection = dBUtil.getConnection();

            String query = "insert into employee(name, email, cell, address) values(?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getCell());
            ps.setString(4, employee.getAddress());

            ps.executeUpdate();
            ps.close();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readEmployees() {
        try {
            connection = dBUtil.getConnection();

            String query = "select * from employee;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getLong("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setCell(rs.getString("cell"));
                employee.setAddress(rs.getString("address"));
                System.out.println(employee.toString());
            }
            rs.close();
            ps.close();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateEmployee(Employee employee) {
        try {
            connection = dBUtil.getConnection();

            String query = "update employee set name = ?, email = ?, cell = ?, address = ? where id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getCell());
            ps.setString(4, employee.getAddress());
            ps.setLong(5, employee.getId());

            ps.executeUpdate();
            ps.close();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmployee(Employee employee) {
        try {
            connection = dBUtil.getConnection();

            String query = "delete from employee where id = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, employee.getId());

            ps.executeUpdate();
            ps.close();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

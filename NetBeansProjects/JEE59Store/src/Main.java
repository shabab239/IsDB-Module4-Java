
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.UIManager;
import store.JEE59Store;
import util.DBUtil;

/**
 *
 * @author shaba
 */
public class Main {

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
//        try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("Nimbus look-and-feel not available, using default.");
//        }
//
//        java.awt.EventQueue.invokeLater(() -> {
//            new JEE59Store().setVisible(true);
//        });

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = new DBUtil().getConnection();

            PreparedStatement ps = connection.prepareStatement("select * from product;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

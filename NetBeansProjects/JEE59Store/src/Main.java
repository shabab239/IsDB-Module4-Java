
import javax.swing.UIManager;
import store.JEE59Store;


/**
 *
 * @author Shabab Ahmed
 */
public class Main {

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Nimbus look-and-feel not available, using default.");
        }

        java.awt.EventQueue.invokeLater(() -> {
            new JEE59Store().setVisible(true);
        });
    }
    
}

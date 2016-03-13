package PCNMClient;

import Entities.Employee;
import PCNMClient.PCNMClientView.LoginSCR;
import javax.swing.JPanel;

/**
 *
 * @author Ori Ziv
 */
public class PCNMClientStart {
    public static PCNMClient client = null;
    public static Employee user;
    public static LoginSCR appWindow;
    
    public static void switchPanels (JPanel newPanel) {
        appWindow.getContentPane().removeAll();
        appWindow.setPreferredSize(newPanel.getPreferredSize());
        appWindow.getContentPane().add(newPanel);
        newPanel.setVisible(true);
        appWindow.repaint();
    }
    
    public static void closeAppWindow () {
        appWindow.removeAll();
        appWindow.dispose();
    }
    
    public static void main(String[] args) {
        appWindow = new LoginSCR();
        appWindow.setVisible(true);
    }
}

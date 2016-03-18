package PCNMClient;

import Entities.Employee;
import PCNMClient.PCNMClientView.LoginSCR;
import javax.swing.JPanel;

/**
 * This class controls the client and the GUI screens
 * @author Ori Ziv
 */
public class PCNMClientStart {

    /**
     * PCNM client instance
     */
    public static PCNMClient client = null;

    /**
     * Current logged in user
     */
    public static Employee user;

    /**
     * GUI's frame
     */
    public static LoginSCR appWindow;
    
    /**
     * This method switches panels on the GUI's frame
     * @param newPanel
     */
    public static void switchPanels (JPanel newPanel) {
        // clear old panel
        appWindow.getContentPane().removeAll();
        // adjust frame's size
        appWindow.setSize(newPanel.getMinimumSize());
        // load new panel
        appWindow.getContentPane().add(newPanel);
        newPanel.setVisible(true);
        appWindow.repaint();
        appWindow.setVisible(true);
    }
    
    /**
     *  This method removes panels and disposes the GUI
     */
    public static void closeAppWindow () {
        appWindow.removeAll();
        appWindow.dispose();
    }
    
    /**
     * Client's main method
     * @param args
     */
    public static void main(String[] args) {
        appWindow = new LoginSCR();
        appWindow.setVisible(true);
    }
}

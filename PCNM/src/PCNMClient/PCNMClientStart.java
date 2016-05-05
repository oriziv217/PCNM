package PCNMClient;

import Entities.Employee;
import PCNMClient.PCNMClientView.LoginSCR;
import java.util.ArrayList;
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
     * Current entities (workstations, workstation types and so on...)
     */
    public static CurrentEntities cur_ent = new CurrentEntities();
    
    /**
     * GUI's frame
     */
    public static LoginSCR appWindow;
    
    /**
     * saved workstation search filters
     */
    public static ArrayList<String>workstationSearchFilters = new ArrayList<String>();

    /**
     * extended flag indicates data loading process state
     */
    public static int gotAllData;
    
    /**
     * This method switches panels on the GUI's frame
     * @param newPanel
     */
    public static void switchPanels (JPanel newPanel) {
        // clear old panel
        appWindow.getContentPane().removeAll();
        // adjust frame's size and possiotion
        appWindow.setSize(newPanel.getMinimumSize());
        appWindow.setLocationRelativeTo(null);
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

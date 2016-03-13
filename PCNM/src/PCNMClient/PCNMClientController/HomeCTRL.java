package PCNMClient.PCNMClientController;

import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import java.io.IOException;

/**
 * This class implements Home screen controls
 * @author ori ziv
 */
public class HomeCTRL {

    /**
     * This method implements close button pressed event
     */
    public static void closeBtnPressed () {
        // load login screen
        PCNMClientStart.switchPanels(PCNMClientStart.appWindow.getPnlLogin());
    }
    
    /**
     * This method implements Quit Button pressed event
     */
    public static void QuitBtnPressed () {
        // Close windows
        PCNMClientStart.closeAppWindow();
        try {
            // close Client
            PCNMClientModel.killConnection();
        } catch (IOException ex) {
            System.exit(0);
        }
    }
}

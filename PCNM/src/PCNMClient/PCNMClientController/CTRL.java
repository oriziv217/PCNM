
package PCNMClient.PCNMClientController;

import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import java.io.IOException;

/**
 * This class implements some common controller functionality
 * @author ori ziv
 */
public class CTRL {
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

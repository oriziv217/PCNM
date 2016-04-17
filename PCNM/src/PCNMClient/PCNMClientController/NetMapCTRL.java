package PCNMClient.PCNMClientController;

import Entities.Message;
import Entities.MessageType;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.HomeSCR;
import PCNMClient.PCNMClientView.PCCompSCR;
import PCNMClient.PCNMClientView.UserTypeSCR;
import java.io.IOException;

/**
 * This class implements the network mapping screen controllers
 * @author ori ziv
 */
public class NetMapCTRL extends CTRL {
    
    /**
     * This method implements close button pressed event on the network mapping screen
     */
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new HomeSCR());
    }

    /**
     * This method implements Manage PC-User-Types button pressed event
     * @throws IOException
     */
    public static void btnMngUserTypPressed() throws IOException {
        PCNMClientStart.switchPanels(new UserTypeSCR());
        Message msg = new Message(MessageType.GET_ALL_USERS);
        PCNMClientModel.sendMessageToServer(msg);
    }

    /**
     * This method implements Manage Workstations button pressed event
     * @throws IOException
     */
    public static void btnMngWorkstationsPressed() throws IOException {
        Message msg = new Message(MessageType.OPEN_WORKSTATION_SCREEN);
        PCNMClientModel.sendMessageToServer(msg);
    }

    /**
     * This method implements Manage PC Components button pressed event
     */
    public static void btnMngPCCompPressed() {
        PCNMClientStart.switchPanels(new PCCompSCR());
    }
}

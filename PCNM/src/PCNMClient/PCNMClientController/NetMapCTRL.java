package PCNMClient.PCNMClientController;

import Entities.Component;
import Entities.Message;
import Entities.MessageType;
import Entities.PCSpec;
import Entities.Status;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.HomeSCR;
import PCNMClient.PCNMClientView.PCCompSCR;
import PCNMClient.PCNMClientView.UserTypeSCR;
import java.io.IOException;
import java.util.ArrayList;

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

    /**
     * This method implements Manage PCs button pressed event
     * @throws IOException
     */
    public static void btnMngPCPressed() throws IOException {
        Component cmp = new Component(null, null, 0, 0, Status.ENABLE);
        PCSpec spc = new PCSpec(null, null, 0, 0, 0, Status.ENABLE);
        PCNMClientStart.gotAllData = 0;
        PCNMClientModel.sendMessageToServer(new Message(MessageType.GET_COMP_ENABLE, cmp));
        PCCTRL.setEnaSpec(new ArrayList<PCSpec>());
        PCNMClientModel.sendMessageToServer(new Message(MessageType.GET_SPEC_ENABLE, spc));
    }
}

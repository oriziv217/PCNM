package PCNMClient.PCNMClientController;

import Entities.Message;
import Entities.MessageType;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.HomeSCR;
import java.io.IOException;

/**
 *
 * @author ori ziv
 */
public class NetMapCTRL extends CTRL {
    
    /**
     *
     */
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new HomeSCR());
    }

    public static void btnMngUserTypPressed() throws IOException {
        Message msg = new Message(MessageType.GET_ALL_USERS);
        PCNMClientModel.sendMessageToServer(msg);
    }
}

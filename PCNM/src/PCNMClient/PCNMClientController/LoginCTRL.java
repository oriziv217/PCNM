package PCNMClient.PCNMClientController;

import Entities.Employee;
import Entities.Message;
import Entities.MessageType;
import PCNMClient.PCNMClientModel;
import java.io.IOException;

/**
 * This method process login credentials and send them to the server
 * @author ori ziv
 */
public class LoginCTRL {

    /**
     * This method implements login button pressed event in the log in screen
     * @param userName
     * @param password
     * @throws IOException
     */
    public static void loginBtnPressed(String userName, char[] password) throws IOException {
        // Build message to server
        Employee emp = new Employee(userName, password);
        Message msg = new Message(MessageType.LOGIN, emp);
        // send to server
        PCNMClientModel.sendMessageToServer(msg);
    }

}
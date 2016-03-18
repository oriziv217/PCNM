package PCNMClient;

import Entities.Employee;
import Entities.Message;
import PCNMClient.PCNMClientView.*;
import com.lloseng.ocsf.client.AbstractClient;
import java.io.IOException;
import ocsf.client.AbstractClient;

/**
 * This class implements the system's client
 * @author ori ziv
 */
public class PCNMClient extends AbstractClient {

    /**
     * Constructor that starts client-server session
     * @param host - Server's host name or IP address
     * @param port - Server's listening port number
     * @throws IOException
     */
    public PCNMClient(String host, int port) throws IOException {
        super(host, port);
        openConnection();
    }

    /**
     * This method receives message from the server and route it to the right controller method
     * @param msg
     */
    @Override
    protected void handleMessageFromServer(Object msg) {
        Message response = (Message)msg;
        switch (response.getMsgType()) {
            case LOGIN_ANSWER:
                if (response.getDataString().equals("User logged-in")) {
                    PCNMClientStart.user = new Employee((Employee)response.getEntity());
                    PCNMClientStart.switchPanels(new HomeSCR());
                } else {
                    PCNMClientStart.appWindow.badLogin(response.getDataString());
                }
                break;
        }
    }
    
    /**
     * This method closes client-server session
     * @throws java.io.IOException
     */
    public void quit() throws IOException {
        closeConnection();
    }
    
}

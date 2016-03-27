package PCNMClient;

import Entities.Employee;
import Entities.Message;
import Entities.PCUserType;
import PCNMClient.PCNMClientController.HomeCTRL;
import PCNMClient.PCNMClientController.UserTypesCTRL;
import PCNMClient.PCNMClientView.*;
import java.io.IOException;
import java.util.ArrayList;
import ocsf.client.AbstractClient;

/**
 * This class implements the system's client
 * @author ori ziv
 */
public class PCNMClient extends AbstractClient {

    private ArrayList<String>search_results;
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
            case GET_EMPLOYEES:
                search_results = HomeCTRL.processGetEmployeesResponse((ArrayList<Employee>) response.getEntity());
                PCNMClientStart.switchPanels(new EmployeeSCR(search_results));
                break;
            case GET_ALL_USERS:
                search_results = UserTypesCTRL.processGetUserTypesResponse((ArrayList<PCUserType>) response.getEntity());
                break;
            
            case DB_PROBLEM:
                WindowMustHave.showDialog(null, response.getDataString(), DialogType.ERROR);
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

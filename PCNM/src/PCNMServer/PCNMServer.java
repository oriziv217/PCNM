package PCNMServer;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import Entities.*;
import PCNMServer.ServerLogic.EmployeesLogic;
import PCNMServer.ServerLogic.UserTypesLogic;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * This class implements system's server
 * @author Ori Ziv
 */
public class PCNMServer extends AbstractServer {

    /**
     * default port used for client-server communication
     */
    public static final int DEFAULT_PORT = 11111;
    
    /**
     * Default constructor
     */
    public PCNMServer () {
        super (DEFAULT_PORT);
    }
    
    /**
     * Constructor
     * @param port client-server communication port
     */
    public PCNMServer (int port) {
        super (port);
    }
    
    // methods
    /**
     * This method handles any messages received from client.
     * @param msg The message received from the client.
     * @param client The connection from which the message originated.
     */
    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message message = (Message)msg;
        
        try {
            switch (message.getMsgType()) {
            case LOGIN:
                try {
                    client.sendToClient(EmployeesLogic.LoginRequest((Employee)message.getEntity()));
                } catch (SQLException ex) {
                    
                }
                break;
            case GET_EMPLOYEES:
                client.sendToClient(EmployeesLogic.getAllEntities());
                break;
            case ADD_EMPLOYEE:
                client.sendToClient(EmployeesLogic.addEmployee((Employee)message.getEntity()));
                break;
            case UPDATE_EMPLOYEES:
                client.sendToClient(EmployeesLogic.updateEmployees((ArrayList<Employee>)message.getEntity()));
                break;
            case GET_ALL_USERS:
                message = (Message)UserTypesLogic.getAllEntities();
                client.sendToClient(message);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, String.format("Lost connection with client %s", client.toString()));
        } catch (SQLException ex) {
            try {
                client.sendToClient(new Message(MessageType.DB_PROBLEM, ex.getMessage()));
            } catch (IOException ex1) {
                JOptionPane.showMessageDialog(null, String.format("Lost connection with client %s", client.toString()));
            }
        }
    }
}

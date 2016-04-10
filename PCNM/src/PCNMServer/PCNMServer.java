package PCNMServer;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import Entities.*;
import PCNMServer.ServerLogic.EmployeesLogic;
import PCNMServer.ServerLogic.UserTypesLogic;
import PCNMServer.ServerLogic.WorkstationLogic;
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
                client.sendToClient(EmployeesLogic.LoginRequest((Employee)message.getEntity()));
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
                break;
            case ADD_PC_USER_TYPE:
                client.sendToClient(UserTypesLogic.addPCUserType((PCUserType)message.getEntity()));
                break;
            case UPDATE_PC_USER_TYPE:
                client.sendToClient(UserTypesLogic.updatePCUserType((PCUserType)message.getEntity()));
                break;
            case OPEN_WORKSTATION_SCREEN:
                client.sendToClient(WorkstationLogic.getAllTypes());
                break;
            case GET_WORKSTATIOS_WITH_FILTER:
                client.sendToClient(WorkstationLogic.getWorkstationsWithFilter((Workstation)message.getEntity()));
                break;
            case GET_WORKSTATION_QUICKDIC:
                client.sendToClient(WorkstationLogic.createQuickDic());
                break;
            case ADD_WORKSTATION:
                client.sendToClient(WorkstationLogic.addWorkstation((Workstation)message.getEntity()));
                break;
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

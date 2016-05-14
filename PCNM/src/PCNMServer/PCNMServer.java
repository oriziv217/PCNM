package PCNMServer;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import Entities.*;
import PCNMServer.ServerLogic.ComponentLogic;
import PCNMServer.ServerLogic.EmployeesLogic;
import PCNMServer.ServerLogic.PCLogic;
import PCNMServer.ServerLogic.PCSpecLogic;
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
                ArrayList<Workstation> wsList = WorkstationLogic.getWorkstationsWithFilter((Workstation)message.getEntity());
                if (wsList == null)
                    client.sendToClient(new Message(MessageType.DB_PROBLEM, null, "DB Error"));
                else
                    client.sendToClient(new Message(MessageType.GET_WORKSTATIOS_WITH_FILTER, wsList, ""));
                break;
            case GET_WORKSTATION_QUICKDIC:
                client.sendToClient(WorkstationLogic.createQuickDic());
                break;
            case ADD_WORKSTATION:
                client.sendToClient(WorkstationLogic.addWorkstation((Workstation)message.getEntity()));
                break;
            case UPDATE_WORKSTATION:
                client.sendToClient(WorkstationLogic.updateWorkstation((Workstation)message.getEntity()));
                break;
            case MANAGE_WSTYPES:
                client.sendToClient(new Message(MessageType.MANAGE_WSTYPES, WorkstationLogic.getAllTypes().getEntity()));
                break;
            case ADD_WSTYPE:
                client.sendToClient(WorkstationLogic.addWSType((WSType)message.getEntity()));
                break;
            case UPDATE_WSTYPE:
                client.sendToClient(WorkstationLogic.updateWSType((WSType)message.getEntity()));
                break;
            case GET_COMP_WITH_FILTER:
                client.sendToClient(ComponentLogic.getComponentsWithFilter(message.getMsgType(), (Component)message.getEntity()));
                break;
            case GET_COMP_QUICKDIC:
                client.sendToClient(ComponentLogic.createQuickDic());
                break;
            case ADD_COMPONENT:
                client.sendToClient(ComponentLogic.addComponent((Component)message.getEntity()));
                break;
            case UPDATE_COMPONENT:
                client.sendToClient(ComponentLogic.updateComponent((Component)message.getEntity()));
                break;
            case GET_COMP_ENABLE:
                client.sendToClient(ComponentLogic.getComponentsWithFilter(message.getMsgType(), (Component)message.getEntity()));
                break;
            case GET_SPEC_ENABLE:
                client.sendToClient(PCSpecLogic.getSpecsWithFilter(message.getMsgType(), (PCSpec)message.getEntity()));
                break;
            case PC_SEARCH:
                client.sendToClient(PCLogic.searchPCByFilter((PC)message.getEntity(), message.getDataString()));
                break;
            case GET_PC_QUICKDIC:
                client.sendToClient(PCLogic.createQuickDic());
                break;
            case ADD_PC:
                client.sendToClient(PCLogic.addPC((PC)message.getEntity()));
                break;
            case UPDATE_PC:
                client.sendToClient(PCLogic.updatePC((PC)message.getEntity()));
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

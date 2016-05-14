package PCNMClient;

import Entities.Component;
import Entities.Employee;
import Entities.Message;
import Entities.MessageType;
import Entities.PC;
import Entities.PCComp;
import Entities.PCSpec;
import Entities.PCUserType;
import Entities.QuickDic;
import Entities.WSType;
import Entities.Workstation;
import PCNMClient.PCNMClientController.ComponentCTRL;
import PCNMClient.PCNMClientController.HomeCTRL;
import PCNMClient.PCNMClientController.PCCTRL;
import PCNMClient.PCNMClientController.UserTypesCTRL;
import PCNMClient.PCNMClientController.WorkstationCTRL;
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
                PCNMClientStart.switchPanels(new UserTypeSCR(search_results));
                break;
            case GET_WORKSTATIOS_WITH_FILTER:
                ArrayList<Workstation> WS_search_resaults = (ArrayList<Workstation>)response.getEntity();
                WorkstationCTRL.processSearchResults(WS_search_resaults);
                try {
                    PCNMClientModel.sendMessageToServer(new Message(MessageType.GET_WORKSTATION_QUICKDIC));
                } catch (IOException ex) {}
                break;
            case GET_WORKSTATION_QUICKDIC:
                WorkstationCTRL.setWSDic((ArrayList<QuickDic>) response.getEntity());
                break;
            case GET_ALL_WORKSTATION_TYPES:
                WorkstationCTRL.openWorkstationWindow((ArrayList<WSType>) response.getEntity());
                break;
            case ADD_WORKSTATION:
                if (response.getDataString().equals("Not OK"))
                    WindowMustHave.showDialog(null, "Error acurred while tring to add Waorkstation to the DB.\n"
                            + "Please contact your System Administrator", DialogType.ERROR);
                else
                    WorkstationCTRL.refreshWorkstationWindow(response.getMsgType(), (Workstation)response.getEntity());
                break;
            case UPDATE_WORKSTATION:
                if (response.getDataString().equals("Not OK"))
                    WindowMustHave.showDialog(null, "Error acurred while tring to update Waorkstation to the DB.\n"
                            + "Please contact your System Administrator", DialogType.ERROR);
                else
                    WorkstationCTRL.refreshWorkstationWindow(response.getMsgType(), (Workstation)response.getEntity());
                break;
            case MANAGE_WSTYPES:
                WorkstationCTRL.openWSTypeMngScreen((ArrayList<WSType>)response.getEntity());
                break;
            case ADD_WSTYPE:
                if (response.getDataString().equals("Not OK"))
                    WindowMustHave.showDialog(null, "Error acurred while tring to add Waorkstation Type to the DB.\n"
                            + "Please contact your System Administrator", DialogType.ERROR);
                else
                    WorkstationCTRL.refreshWSTypeWindow(response.getMsgType(), (WSType)response.getEntity());
                break;
            case UPDATE_WSTYPE:
                if (response.getDataString().equals("Not OK"))
                    WindowMustHave.showDialog(null, "Error acurred while tring to update Waorkstation Type to the DB.\n"
                            + "Please contact your System Administrator", DialogType.ERROR);
                else if (response.getDataString().equals("Depandancy Error")) {
                    WindowMustHave.showDialog(null, "Can not update Workstation Type's status\n"
                            + "while Workstations of this type exists.", DialogType.INFO);
                    WorkstationCTRL.refreshWSTypeWindow(MessageType.UPDATE_WSTYPE, null);
                } else
                    WorkstationCTRL.refreshWSTypeWindow(response.getMsgType(), (WSType)response.getEntity());
                break;
            case GET_COMP_WITH_FILTER:
                ComponentCTRL.processSearchResults((ArrayList<Component>)response.getEntity());
                try {
                    PCNMClientModel.sendMessageToServer(new Message(MessageType.GET_COMP_QUICKDIC));
                } catch (IOException ex) {}
                break;
            case GET_COMP_QUICKDIC:
                ComponentCTRL.setCompDic((ArrayList<QuickDic>) response.getEntity());
                break;
            case ADD_COMPONENT:
                if (response.getDataString().equals("Not OK"))
                    WindowMustHave.showDialog(null, "Error acurred while tring to add Component to the DB.\n"
                            + "Please contact your System Administrator", DialogType.ERROR);
                else
                    ComponentCTRL.refreshComponentWindow(response.getMsgType(), (Component)response.getEntity());
                break;
            case UPDATE_COMPONENT:
                if (response.getDataString().equals("Not OK"))
                    WindowMustHave.showDialog(null, "Error acurred while tring to update Component to the DB.\n"
                            + "Please contact your System Administrator", DialogType.ERROR);
                else if (response.getDataString().equals("This component is installed on PC(s)")) {
                    WindowMustHave.showDialog(null, "This component is installed on PC(s)."
                            + "You must unintall this component before you can disable it.", DialogType.INFO);
                    ComponentCTRL.refreshComponentWindow(response.getMsgType(), null);
                } else
                    ComponentCTRL.refreshComponentWindow(response.getMsgType(), (Component)response.getEntity());
                break;
            case GET_COMP_ENABLE:
                PCCTRL.setEnaComp((ArrayList<Component>)response.getEntity());
                PCNMClientStart.gotAllData ++;
                if (PCNMClientStart.gotAllData == 2)
                    PCCTRL.openPCSCR();
                break;
            case GET_SPEC_ENABLE:
                PCCTRL.setEnaSpec((ArrayList<PCSpec>)response.getEntity());
                PCNMClientStart.gotAllData ++;
                if (PCNMClientStart.gotAllData == 2)
                    PCCTRL.openPCSCR();
                break;
            case PC_SEARCH:
                PCCTRL.processPCSearchReasult((ArrayList<PC>)response.getEntity());
                try {
                    PCNMClientModel.sendMessageToServer(new Message(MessageType.GET_PC_QUICKDIC));
                } catch (IOException ex) {}
                break;
            case GET_PC_QUICKDIC:
                PCCTRL.setPCDic((ArrayList<QuickDic>) response.getEntity());
                break;
            case ADD_PC:
                if (response.getDataString().equals("Not OK"))
                    WindowMustHave.showDialog(null, "Error acurred while tring to add PC to the DB.\n"
                            + "Please contact your System Administrator", DialogType.ERROR);
                else
                    PCCTRL.refreshPCWindow(response.getMsgType(), (PC)response.getEntity());
                break;
            case UPDATE_PC:
                if (response.getDataString().equals("Not OK"))
                    WindowMustHave.showDialog(null, "Error acurred while tring to update PC to the DB.\n"
                            + "Please contact your System Administrator", DialogType.ERROR);
                else
                    PCCTRL.refreshPCWindow(response.getMsgType(), (PC)response.getEntity());
                break;
            case GET_PC_INST_COMP:
                PCCTRL.setPCInstalledComp((ArrayList<PCComp>)response.getEntity());
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

package PCNMClient.PCNMClientController;

import Entities.EmpType;
import Entities.Employee;
import Entities.Message;
import Entities.MessageType;
import Entities.Status;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.EmployeeSCR;
import PCNMClient.PCNMClientView.HomeSCR;
import java.awt.Component;
import java.io.IOException;
import javax.swing.JPanel;

/**
 * this class implements Employee screen controls
 * @author Ori Ziv
 */
public class EmployeeCTRL extends CTRL {
    
    /**
     * Close button pressed event handler
     */
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new HomeSCR());
    }

    /**
     * Add user button pressed event handler
     * @param name
     * @param userName
     * @param password
     * @param type
     * @param status
     * @throws IOException
     */
    public static void btnAddUserOKPressed(String name, String userName, char[] password, String type, String status) throws IOException {
        EmpType typ = null;
        Status sts = null;
        Employee emp;
        switch (type) {
            case "Administrator":
                typ = EmpType.ADMINISTRATOR;
                break;
            case "Technician":
                typ = EmpType.TECHNICIAN;
                break;
            case "MCSE":
                typ = EmpType.MCSE;
                break;
            case "CEO":
                typ = EmpType.CEO;
                break;
        }
        
        switch (status) {
            case "Enabled":
                sts = Status.ENABLE;
                break;
            case "Disabled":
                sts = Status.DISABLE;
                break;
            case "Suspended":
                sts = Status.SUSPENDED;
        }
        
        emp = new Employee(name, userName, password, typ, sts);
        PCNMClientModel.sendMessageToServer(new Message(MessageType.ADD_EMPLOYEE, emp));
    }
}

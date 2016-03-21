package PCNMClient.PCNMClientController;

import Entities.EmpType;
import Entities.Employee;
import Entities.Message;
import Entities.MessageType;
import Entities.Status;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.HomeSCR;
import java.io.IOException;
import java.util.ArrayList;

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

    public static void btnApplyPressed(ArrayList<String> toApply) throws IOException {
        String[] data;
        EmpType typ;
        Status sts;
        ArrayList<Employee> emps = new ArrayList<Employee>();
        
        for (String row : toApply) {
            data = row.split(",");
            switch (data[4]) {
                case "CEO":
                    typ = EmpType.CEO;
                    break;
                case "MCSE":
                    typ = EmpType.MCSE;
                    break;
                case "Technician":
                    typ = EmpType.TECHNICIAN;
                    break;
                case "Administrator":
                    typ = EmpType.ADMINISTRATOR;
                    break;
                default:
                    throw new IOException("Bad Input");
            }
            switch (data[5]) {
                case "Enabled":
                    sts = Status.ENABLE;
                    break;
                case "Disabled":
                    sts = Status.DISABLE;
                    break;
                case "Suspended":
                    sts = Status.SUSPENDED;
                    break;
                default:
                    throw new IOException("Bad Input");
            }
            emps.add(new Employee(Integer.parseInt(data[0]), data[1], data[2], data[3].toCharArray(), typ, sts));
        }
        PCNMClientModel.sendMessageToServer(new Message(MessageType.UPDATE_EMPLOYEES, emps));
    }
}

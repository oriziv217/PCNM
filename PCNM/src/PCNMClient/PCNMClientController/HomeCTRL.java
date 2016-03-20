package PCNMClient.PCNMClientController;

import Entities.Employee;
import Entities.Message;
import Entities.MessageType;
import PCNMClient.PCNMClientModel;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class implements Home screen controls
 * @author ori ziv
 */
public class HomeCTRL extends CTRL {

    /**
     * This method implements btnEmployees pressing event
     * @throws IOException
     */
    public static void btnEmployeesPressed() throws IOException{
        Message msg = new Message(MessageType.GET_EMPLOYEES);
        PCNMClientModel.sendMessageToServer(msg);
    }

    /**
     * This method process server's response to get employees command
     * @param empList
     * @return
     */
    public static ArrayList<String> processGetEmployeesResponse(ArrayList<Employee> empList) {
        ArrayList<String> search_results = new ArrayList<String>();
        for (Employee emp : empList)
            search_results.add(emp.toString());
        return search_results;
    }
}

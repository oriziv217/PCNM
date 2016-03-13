package PCNMServer.ServerLogic;

import Entities.EmpType;
import Entities.Employee;
import Entities.Message;
import Entities.MessageType;
import Entities.Status;
import PCNMServer.ServerResources.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class implements application procedures related to employees
 * @author Ori Ziv
 */
public class EmployeesLogic {

    /**
     * This method verify login request with the DB
     * @param emp
     * @return
     * @throws SQLException
     */
    public static Message LoginRequest (Employee emp) throws SQLException {
        // set credentials
        String username = emp.getUserName();
        String password = String.valueOf(emp.getPassword());
        // query DB
        String filter = "userName = '" + username + "'";
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs = DBConnect.selectWithFilter(conDB, "Employees", null, filter);
        // if result set is not empty then username is a match
        if (rs.next()) {
            // verify password
            if (password.equals(rs.getString("password"))) {
                // verify employee's status
                if (rs.getInt("status") == 1) {
                    // set employee's data
                    emp.setID(rs.getInt("ID"));
                    emp.setName(rs.getString("name"));
                    int type = rs.getInt("type");
                    switch (type) {
                        case 1:
                            emp.setType(EmpType.CEO);
                            break;
                        case 2:
                            emp.setType(EmpType.MCSE);
                            break;
                        case 3:
                            emp.setType(EmpType.TECHNICIAN);
                            break;
                        case 4:
                            emp.setType(EmpType.ADMINISTRATOR);
                            break;
                    }
                    emp.setStatus(Status.ENABLE);
                } else {
                    return new Message(MessageType.LOGIN_ANSWER, "Employee status is not Active");
                }
            } else {
                return new Message(MessageType.LOGIN_ANSWER, "Bad Password");
            }
            } else {
                return new Message(MessageType.LOGIN_ANSWER, "Bad User Name or Password");
            }
        return new Message(MessageType.LOGIN_ANSWER, emp, "User logged-in");
    }
}

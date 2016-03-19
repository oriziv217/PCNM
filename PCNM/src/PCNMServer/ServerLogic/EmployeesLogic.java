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
import java.util.ArrayList;

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
        ResultSet rs = DBConnect.selectWithFilter(conDB, "Employee", null, filter);
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

    public static Object getAllEntities() throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs = DBConnect.selectWithFilter(conDB, "employee", null, null);
        ArrayList<Employee> emp_tbl = new ArrayList<Employee>();
        Employee row = new Employee();
        
        while (rs.next()) {
            row.setID(rs.getInt("ID"));
            row.setName(rs.getString("name"));
            row.setUserName(rs.getString("userName"));
            row.setPassword(rs.getString("password").toCharArray());
            row.setType(extractEmpType(rs.getInt("type")));
            row.setStatus(extractStatus(rs.getInt("status")));
            if (row.getType() != EmpType.Error && row.getStatus() != Status.Error)
                emp_tbl.add(new Employee(row.getID(), row.getName(), row.getUserName(), row.getPassword(), row.getType(), row.getStatus()));
        }
        return new Message(MessageType.GET_EMPLOYEES, emp_tbl);
    }

    private static EmpType extractEmpType(int dbType) {
        switch (dbType) {
            case 1:
                return EmpType.TECHNICIAN;
            case 2:
                return EmpType.MCSE;
            case 3:
                return EmpType.CEO;
            case 4:
                return EmpType.ADMINISTRATOR;
        }
        return EmpType.Error;
    }

    private static Status extractStatus(int dbStatus) {
        switch (dbStatus) {
            case 1:
                return Status.ENABLE;
            case 2:
                return Status.DISABLE;
            case 3:
                return Status.SUSPENDED;
        }
        return Status.Error;
    }
}

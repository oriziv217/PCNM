package PCNMServer.ServerLogic;

import Entities.EmpType;
import Entities.Employee;
import Entities.Message;
import Entities.MessageType;
import Entities.Status;
import PCNMServer.ServerResources.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class implements application procedures related to employees
 * @author Ori Ziv
 */
public class EmployeesLogic extends Logic {

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

    /**
     * This method retrieve all employee records from the DB and return a message with array of employees
     * @return
     * @throws SQLException
     */
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
            row.setType(intToEmpType(rs.getInt("type")));
            row.setStatus(intToStatus(rs.getInt("status")));
            if (row.getType() != EmpType.Error && row.getStatus() != Status.Error)
                emp_tbl.add(new Employee(row.getID(), row.getName(), row.getUserName(), row.getPassword(), row.getType(), row.getStatus()));
        }
        return new Message(MessageType.GET_EMPLOYEES, emp_tbl);
    }

    private static EmpType intToEmpType(int dbType) {
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

    private static int EmpTypeToInt(EmpType type) {
        switch (type) {
            case TECHNICIAN:
                return 1;
            case MCSE:
                return 2;
            case CEO:
                return 3;
            case ADMINISTRATOR:
                return 4;
        }
        return 5;
    }

    /**
     * This method adds an employee record to the DB
     * @param employee
     * @return
     * @throws SQLException
     */
    public static Object addEmployee(Employee employee) throws SQLException {
        int typ = EmpTypeToInt(employee.getType());
        int sts = statusToInt(employee.getStatus());
        if (typ == 5 || sts == 4) throw new SQLException("Invalid input");
        
        String[] fields = {"name", "username", "password", "type", "status"};
        String[] values = {employee.getName(), employee.getUserName(), String.valueOf(employee.getPassword()), String.valueOf(typ), String.valueOf(sts)};
        
        Connection conDB = DBConnect.mySQLConnection();
        boolean isSuccess = DBConnect.insertSingleRecord(conDB, "employee", fields, values);
        if (isSuccess) {
            employee.setID(-1);
            return getAllEntities();
        }
        throw new SQLDataException("Error adding user " + employee.getName());
    }

    /**
     * this method receives an array of employees to update and perform update query to the DB
     * @param emps
     * @return
     * @throws SQLException
     */
    public static Object updateEmployees(ArrayList<Employee> emps) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        String table = "employee";
        String[] fields = {"ID", "name", "userName", "password", "type", "status"};
        String[] values = new String[6];
        String[] keyName = {"ID"};
        String[] keyVal = new String[1];
        boolean updated;
        
        for (Employee emp : emps) {
            keyVal[0] = String.valueOf(emp.getID());
            values[0] = String.valueOf(emp.getID());
            values[1] = emp.getName();
            values[2] = emp.getUserName();
            values[3] = String.valueOf(emp.getPassword());
            values[4] = String.valueOf(EmpTypeToInt(emp.getType()));
            values[5] = String.valueOf(statusToInt(emp.getStatus()));
            updated = DBConnect.updateSingleRecord(conDB, table, fields, values, keyName, keyVal);
            if (!updated)
                throw new SQLException("DB Problem");
        }
        return getAllEntities();
    }
}

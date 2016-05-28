package PCNMServer.ServerLogic;

import Entities.Message;
import Entities.MessageType;
import Entities.PCUserType;
import Entities.Status;
import PCNMServer.ServerResources.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class implements PC user types server side commands
 * @author ori ziv
 */
public class UserTypesLogic extends Logic {

    /**
     * This method retrieve all employee records from the DB and return a message with array of employees
     * @return
     * @throws SQLException
     */
    public static Object getAllEntities() throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs = DBConnect.selectWithFilter(conDB, "pcusertype", null, null);
        ArrayList<PCUserType> users_tbl = new ArrayList<PCUserType>();
        PCUserType row = new PCUserType();
        
        while (rs.next()) {
            row.setID(rs.getInt("ID"));
            row.setName(rs.getString("name"));
            row.setDescription(rs.getString("description"));
            row.setImportance(rs.getDouble("importance"));
            row.setStatus(intToStatus(rs.getInt("status")));
            if (row.getStatus() != Status.Error)
                users_tbl.add(new PCUserType(row.getID(), row.getName(), row.getDescription(), row.getImportance(), row.getStatus()));
        }
        conDB.close();
        return new Message(MessageType.GET_ALL_USERS, users_tbl);
    }

    /**
     * This method implements server side add PC user type command
     * @param pcUserType
     * @return
     * @throws SQLException
     */
    public static Object addPCUserType(PCUserType pcUserType) throws SQLException {
        int sts = statusToInt(pcUserType.getStatus());
        if (sts == 4) throw new SQLException("Invalid input");
        
        String[] fields = {"name", "description", "importance", "status"};
        String[] values = {pcUserType.getName(), pcUserType.getDescription(), String.valueOf(pcUserType.getImportance()), String.valueOf(sts)};
        
        Connection conDB = DBConnect.mySQLConnection();
        boolean isSuccess = DBConnect.insertSingleRecord(conDB, "pcusertype", fields, values);
        if (isSuccess) {
            pcUserType.setID(-1);
            return getAllEntities();
        }
        throw new SQLDataException("Error adding user " + pcUserType.getName());
    }

    /**
     * This method implements server side update PC user type command
     * @param pcUserType
     * @return
     * @throws SQLException
     */
    public static Object updatePCUserType(PCUserType pcUserType) throws SQLException {
        if (isUpdateAllowed(pcUserType)) {
            int sts = statusToInt(pcUserType.getStatus());
            if (sts == 4) throw new SQLException("Invalid input");

            String[] fields = {"id", "name", "description", "importance", "status"};
            String[] values = {String.valueOf(pcUserType.getID()), pcUserType.getName(), pcUserType.getDescription(), String.valueOf(pcUserType.getImportance()), String.valueOf(sts)};
            String[] keyName = {"id"};
            String[] keyVal = {String.valueOf(pcUserType.getID())};
            Connection conDB = DBConnect.mySQLConnection();
            boolean isSuccess = DBConnect.updateSingleRecord (conDB, "pcusertype", fields, values, keyName, keyVal);
            conDB.close();
            if (isSuccess)
                return getAllEntities();
            throw new SQLException("Error updating user " + pcUserType.getName());
        }
        throw new SQLException("This User Type is referenced in an active workstation-PC connection");
    }
    
    protected static boolean isUpdateAllowed(PCUserType user) throws SQLException {
        if (user.getStatus() != Status.DISABLE) return true;
        
        Connection conDB = DBConnect.mySQLConnection();
        String table = "triocoupling";
        String fields = "COUNT(*) AS LINES_NUM";
        String filter = "PCUserTypeID = " + user.getID() + " AND dueDate IS NULL";
        ResultSet rs = DBConnect.selectWithFilter(conDB, table, fields, filter);

        if (!rs.first()) throw new SQLException("Error reading DB");
        int lines_num = rs.getInt("LINES_NUM");
        conDB.close();
        if (lines_num > 0) return false;
        return true;
    }
    
    public static PCUserType getPCUserTypeByID (int ID) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        PCUserType pcut = new PCUserType(ID);
        ResultSet rs;
        String filter = "id = " + ID;
        rs = DBConnect.selectWithFilter(conDB, "pcusertype", null, filter);
        
        if (rs.first()) {
            pcut.setName(rs.getString("name"));
            pcut.setDescription(rs.getString("description"));
            pcut.setImportance(rs.getDouble("importance"));
            pcut.setStatus(intToStatus(rs.getInt("status")));
        }
        conDB.close();
        return pcut;
    }

    public static Message getActivePCUserTypes() throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        String filter = "status = 1";
        ResultSet rs = DBConnect.selectWithFilter(conDB, "pcusertype", null, filter);
        ArrayList<PCUserType> search_reasults = new ArrayList<PCUserType>();
        
        if (rs.isBeforeFirst()) {
            while (rs.next()) {
                search_reasults.add(new PCUserType( rs.getInt("ID"),
                                                    rs.getString("name"),
                                                    rs.getString("description"),
                                                    roundDouble(rs.getDouble("importance"), 2),
                                                    intToStatus(rs.getInt("status"))));
            }
        }
        conDB.close();
        return new Message(MessageType.GET_PCUSERTYPE_ADD_TRIO, search_reasults);
    }
}

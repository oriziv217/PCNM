package PCNMServer.ServerLogic;

import Entities.Message;
import Entities.MessageType;
import Entities.PCUserType;
import Entities.Status;
import PCNMServer.ServerResources.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
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
            row.setImportance(rs.getFloat("importance"));
            row.setStatus(intToStatus(rs.getInt("status")));
            if (row.getStatus() != Status.Error)
                users_tbl.add(new PCUserType(row.getID(), row.getName(), row.getDescription(), row.getImportance(), row.getStatus()));
        }
        return new Message(MessageType.GET_ALL_USERS, users_tbl);
    }
}

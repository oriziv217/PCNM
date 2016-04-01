package PCNMServer.ServerLogic;

import Entities.Message;
import Entities.MessageType;
import Entities.Status;
import Entities.WSType;
import PCNMServer.ServerResources.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class implements Workstations and Workstation Types business logic
 * @author ori ziv
 */
public class WorkstationLogic extends Logic {

    public static Message getAllTypes() throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs = DBConnect.selectWithFilter(conDB, "wstype", null, null);
        ArrayList<WSType> users_tbl = new ArrayList<WSType>();
        WSType row = new WSType();
        
        while (rs.next()) {
            row.setID(rs.getInt("ID"));
            row.setName(rs.getString("name"));
            row.setDescription(rs.getString("description"));
            row.setMinimalScore(rs.getInt("minimalScore"));
            row.setStatus(intToStatus(rs.getInt("status")));
            if (row.getStatus() != Status.Error)
                //(int ID, String name, String description, int minimalScore, Status status)
                users_tbl.add(new WSType(row.getID(), row.getName(), row.getDescription(), row.getMinimalScore(), row.getStatus()));
        }
        return new Message(MessageType.GET_ALL_WORKSTATION_TYPES, users_tbl);
    }
    
}

package PCNMServer.ServerLogic;

import Entities.Message;
import Entities.MessageType;
import Entities.Status;
import Entities.WSType;
import Entities.Workstation;
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

    private static ArrayList<Integer> getWSTypesByName(String name) throws SQLException {
        ArrayList<Integer> keys = new ArrayList<Integer>();
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        rs = DBConnect.selectWithFilter(conDB, "wstype", "ID", "name = '" + name + "'");
        while (rs.next()) {
            keys.add(rs.getInt("ID"));
        }
        return keys;
    }
    public static Message getAllTypes() throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        try {
            rs = DBConnect.selectWithFilter(conDB, "wstype", null, null);
        } catch (SQLException e) {
            return new Message(MessageType.DB_PROBLEM, null, e.getMessage());
        }
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

    public static Message getWorkstationsWithFilter(Workstation search_model) throws SQLException {
        ArrayList<Workstation> search_results = new ArrayList<Workstation>();
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        // define search results schema
        String[] fields = {"workstation.id",
                            "workstation.name",
                            "workstation.description",
                            "workstation.importance",
                            "workstation.status",
                            "wstype.id",
                            "wstype.name",
                            "wstype.description",
                            "wstype.minimalscore",
                            "wstype.status"};
        String[] labels = {"WSID",
                            "WSNAME",
                            "WSDESCRIPTION",
                            "WSIMPORTANCE",
                            "WSSTATUS",
                            "WSTID",
                            "WSTNAME",
                            "WSTDESCRIPTION",
                            "WSTMINIMALSCORE",
                            "WSTSTATUS"};

        // define join keys
        String[] leftKeys = {"workstation.wstypeid"};
        String[] rightKeys = {"wstype.id"};

        // start building the search filter
        String filter = new String();
        boolean isfirst = true;
        // applying filter by workstation name
        if (!search_model.getName().isEmpty()) {
            filter = "workstation.name = '" + search_model.getName() + "'";
            isfirst = false;
        }
        // applying filter by workstation description
        if (!search_model.getDiscription().isEmpty()) {
            if (isfirst) {
                filter = "workstation.description = '" + search_model.getDiscription() + "'";
                isfirst = false;
            }
            else
                filter = filter + " AND workstation.description = '" + search_model.getDiscription() + "'";
        }
        // applying filter by importance factor
        if (search_model.getImportanceFactor() != 0) {
            double imp = search_model.getImportanceFactor();
            if (isfirst) {
                if (imp == 1)
                    filter = "workstation.importance >= " + imp;
                else
                    filter = "workstation.importance <= " + imp*(-1);
                isfirst = false;
            } else {
                if (imp == 1)
                    filter = filter + " AND workstation.importance >= " + imp;
                else
                    filter = filter + " AND workstation.importance <= " + imp*(-1);
            }
        }
        // applying filter by status
        if (search_model.getStatus() != null) {
            if (isfirst) {
                filter = "workstation.status = " + statusToInt(search_model.getStatus());
                isfirst = false;
            } else
                filter = filter + " AND workstation.status = " + statusToInt(search_model.getStatus());
        }
        // applying filter by workstation type (name)
        if (search_model.getType() != null) {
            if (isfirst) {
                filter = "wstype.name = '" + search_model.getType().getName() + "'";
                isfirst = false;
            } else
                filter = " AND wstype.name = '" + search_model.getType().getName() + "'";
        }
        
        // run query and process resault-set
        try {
            rs = DBConnect.innerJoin(conDB, "workstation", "wstype", leftKeys, rightKeys, fields, labels, filter, null);
            while (rs.next()) {
                search_results.add(new Workstation(rs.getInt("WSID"),
                                                    rs.getString("WSNAME"),
                                                    rs.getString("WSDESCRIPTION"),
                                                    rs.getDouble("WSIMPORTANCE"),
                                                    intToStatus(rs.getInt("WSSTATUS")),
                                                    new WSType(rs.getInt("WSTID"),
                                                                rs.getString("WSTNAME"),
                                                                rs.getString("WSTDESCRIPTION"),
                                                                rs.getInt("WSTMINIMALSCORE"),
                                                                intToStatus(rs.getInt("WSTSTATUS")))));
            }
        } catch (SQLException e) {
            return new Message(MessageType.DB_PROBLEM, null, e.getMessage());
        }
        return new Message(MessageType.GET_WORKSTATIOS_WITH_FILTER, search_results);
    }

    private static WSType getWSTypeByID(int wsTypeID) throws SQLException {
        WSType wst = null;
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        rs = DBConnect.selectWithFilter(conDB, "wstype", null, "id = " + wsTypeID);
        if (rs.first()) {
            wst = new WSType(rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getInt("minimalscore"),
                                intToStatus(rs.getInt("status")));
        }
        return wst;
    }
    
}

package PCNMServer.ServerLogic;

import Entities.Message;
import Entities.MessageType;
import Entities.QuickDic;
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
        conDB.close();
        return keys;
    }

    /**
     * This method get all workstation types from the DB
     * @return
     * @throws SQLException
     */
    public static Message getAllTypes() throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        try {
            rs = DBConnect.selectWithFilter(conDB, "wstype", null, null);
        } catch (SQLException e) {
            return new Message(MessageType.DB_PROBLEM, null, e.getMessage());
        }
        ArrayList<WSType> types_tbl = new ArrayList<WSType>();
        WSType row = new WSType();
        
        while (rs.next()) {
            row.setID(rs.getInt("ID"));
            row.setName(rs.getString("name"));
            row.setDescription(rs.getString("description"));
            row.setMinimalScore(rs.getInt("minimalScore"));
            row.setStatus(intToStatus(rs.getInt("status")));
            if (row.getStatus() != Status.Error)
                //(int ID, String name, String description, int minimalScore, Status status)
                types_tbl.add(new WSType(row.getID(), row.getName(), row.getDescription(), row.getMinimalScore(), row.getStatus()));
        }
        conDB.close();
        return new Message(MessageType.GET_ALL_WORKSTATION_TYPES, types_tbl);
    }

    /**
     * This method get all workstation records (joined with their types) from the DB according to a given filter
     * @param search_model
     * @return
     * @throws SQLException
     */
    public static ArrayList<Workstation> getWorkstationsWithFilter(Workstation search_model) throws SQLException {
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
            filter = "workstation.name LIKE '%" + search_model.getName() + "%'";
            isfirst = false;
        }
        // applying filter by workstation description
        if (!search_model.getDiscription().isEmpty()) {
            if (isfirst) {
                filter = "workstation.description LIKE '%" + search_model.getDiscription() + "%'";
                isfirst = false;
            }
            else
                filter = filter + " AND workstation.description LIKE '%" + search_model.getDiscription() + "%'";
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
        if (search_model.getStatus() != null && search_model.getStatus() != Status.Error) {
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
                filter = filter + " AND wstype.name = '" + search_model.getType().getName() + "'";
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
            return null;
        }
        conDB.close();
        return search_results;
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
        conDB.close();
        return wst;
    }

    /**
     * This method get all workstations names and IDs from the DB in order to create quick dictionary on client side
     * @return
     * @throws SQLException
     */
    public static Message createQuickDic() throws SQLException {
        ArrayList<QuickDic> dic = new ArrayList<QuickDic>();
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        String fields = "ID, name";
        rs = DBConnect.selectWithFilter(conDB, "workstation", fields, null);
        while (rs.next()) {
            dic.add(new QuickDic(rs.getInt("ID"), rs.getString("name")));
        }
        conDB.close();
        return new Message(MessageType.GET_WORKSTATION_QUICKDIC, dic);
    }

    /**
     * This method adds a new record of workstation to the DB
     * @param newWS
     * @return
     * @throws SQLException
     */
    public static Message addWorkstation(Workstation newWS) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        boolean isAdded;
        String resultString;
        String[] fields = { "name",
                            "description",
                            "importance",
                            "status",
                            "wstypeid" };
        String[] values = { newWS.getName(),
                            newWS.getDiscription(),
                            String.valueOf(newWS.getImportanceFactor()),
                            String.valueOf(statusToInt(newWS.getStatus())),
                            String.valueOf(newWS.getType().getID())};
        isAdded = DBConnect.insertSingleRecord (conDB, "workstation", fields, values);
        if (isAdded) {
            resultString = "OK";
            Workstation added = getWorkstationsWithFilter(new Workstation(newWS.getID(), newWS.getName())).get(0);
            conDB.close();
            return new Message (MessageType.ADD_WORKSTATION, added, resultString);
        }
        resultString = "Not OK";
        conDB.close();
        return new Message(MessageType.ADD_WORKSTATION, null, resultString);
    }

    /**
     * This method updates an existing workstation record in the DB
     * @param ws
     * @return
     * @throws SQLException
     */
    public static Message updateWorkstation(Workstation ws) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        boolean isUpdated;
        String resultString;
        String[] fields = { "id",
                            "name",
                            "description",
                            "importance",
                            "status",
                            "wstypeid" };
        String[] values = { Integer.toString(ws.getID()),
                            ws.getName(),
                            ws.getDiscription(),
                            String.valueOf(ws.getImportanceFactor()),
                            String.valueOf(statusToInt(ws.getStatus())),
                            String.valueOf(ws.getType().getID()) };
        String[] keyName = { "id" };
        String[] keyVal = { Integer.toString(ws.getID()) };
        if (!isWorkstationUpdateAllowed(ws)) {
            rs = DBConnect.selectWithFilter(conDB, "workstation", "status", "ID = " + ws.getID());
            if (!rs.first()) throw new SQLException("Error Reading DB");
            ws.setStatus(intToStatus(rs.getInt("status")));
            return new Message(MessageType.UPDATE_WORKSTATION, ws, "This Workstation is referenced to an active PC-User-Type connection");
        }
        isUpdated = DBConnect.updateSingleRecord (conDB, "workstation", fields, values, keyName, keyVal);
        if (isUpdated) {
            resultString = "OK";
            conDB.close();
            return new Message (MessageType.UPDATE_WORKSTATION, ws, resultString);
        }
        resultString = "Not OK";
        conDB.close();
        return new Message(MessageType.UPDATE_WORKSTATION, null, resultString);
    }

    protected static boolean isWorkstationUpdateAllowed(Workstation ws) throws SQLException {
        if (ws.getStatus() != Status.DISABLE) return true;
        
        Connection conDB = DBConnect.mySQLConnection();
        String table = "triocoupling";
        String fields = "COUNT(*) AS LINES_NUM";
        String filter = "workstationID = " + ws.getID() + " AND dueDate IS NULL";
        ResultSet rs = DBConnect.selectWithFilter(conDB, table, fields, filter);

        if (!rs.first()) throw new SQLException("Error reading DB");
        int lines_num = rs.getInt("LINES_NUM");
        conDB.close();
        if (lines_num > 0) return false;
        return true;
    }
    
    protected static boolean isWSTypeUpdateAllowed(WSType wst) throws SQLException {
        if (wst.getStatus() != Status.DISABLE) return true;
        
        Connection conDB = DBConnect.mySQLConnection();
        String table = "workstation";
        String fields = "COUNT(*) AS LINES_NUM";
        String filter = "wstypeID = " + wst.getID() + " AND status <> " + statusToInt(Status.DISABLE);
        ResultSet rs = DBConnect.selectWithFilter(conDB, table, fields, filter);

        if (!rs.first()) throw new SQLException("Error reading DB");
        int lines_num = rs.getInt("LINES_NUM");
        conDB.close();
        if (lines_num > 0) return false;
        return true;
    }
    
    /**
     * This method adds a new workstation type record to the DB
     * @param wst
     * @return
     * @throws SQLException
     */
    public static Message addWSType(WSType wst) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        boolean isAdded;
        String resultString;
        String[] fields = { "name",
                            "description",
                            "minimalScore",
                            "status" };
        String[] values = { wst.getName(),
                            wst.getDescription(),
                            String.valueOf(wst.getMinimalScore()),
                            String.valueOf(statusToInt(wst.getStatus())) };
        isAdded = DBConnect.insertSingleRecord (conDB, "wstype", fields, values);
        if (isAdded) {
            resultString = "OK";
            rs = DBConnect.selectWithFilter(conDB, "wstype", null, "name = '" + wst.getName() + "'");
            rs.first();
            wst.setID(rs.getInt("ID"));
            conDB.close();
            return new Message (MessageType.ADD_WSTYPE, wst, resultString);
        }
        conDB.close();
        resultString = "Not OK";
        return new Message(MessageType.ADD_WSTYPE, null, resultString);
    }

    /**
     * This method updates an existing record of workstation type in the DB
     * @param wst
     * @return
     * @throws SQLException
     */
    public static Message updateWSType(WSType wst) throws SQLException {
        if (!isWSTypeUpdateAllowed(wst))
            return new Message(MessageType.UPDATE_WSTYPE, wst, "This Workstation Type is still in used by Active Workstations");
        
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        String resultString;
        boolean isUpdated;
        
        String[] fields = { "id",
                            "name",
                            "description",
                            "minimalScore",
                            "status" };
        String[] values = { Integer.toString(wst.getID()),
                            wst.getName(),
                            wst.getDescription(),
                            String.valueOf(wst.getMinimalScore()),
                            String.valueOf(statusToInt(wst.getStatus())) };
        String[] keyName = { "id" };
        String[] keyVal = { Integer.toString(wst.getID()) };
        isUpdated = DBConnect.updateSingleRecord (conDB, "wstype", fields, values, keyName, keyVal);
        if (isUpdated) {
            resultString = "OK";
            conDB.close();
            return new Message (MessageType.UPDATE_WSTYPE, wst, resultString);
        }
        resultString = "Not OK";
        conDB.close();
        return new Message(MessageType.UPDATE_WSTYPE, null, resultString);
    }
    
    public static Workstation getWorkstationByID (int ID) throws SQLException {
        Workstation ws = new Workstation(ID);
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        // define search results schema
        String[] fields = { "workstation.id",
                            "workstation.name",
                            "workstation.description",
                            "workstation.importance",
                            "workstation.status",
                            "wstype.id",
                            "wstype.name",
                            "wstype.description",
                            "wstype.minimalscore",
                            "wstype.status" };
        String[] labels = { "WSID",
                            "WSNAME",
                            "WSDESCRIPTION",
                            "WSIMPORTANCE",
                            "WSSTATUS",
                            "WSTID",
                            "WSTNAME",
                            "WSTDESCRIPTION",
                            "WSTMINIMALSCORE",
                            "WSTSTATUS" };

        // define join keys
        String[] leftKeys = { "workstation.wstypeid" };
        String[] rightKeys = { "wstype.id" };

        // start building the search filter
        String filter = "workstation.id = " + ID;

        // run query and process resault-set
        rs = DBConnect.innerJoin(conDB, "workstation", "wstype", leftKeys, rightKeys, fields, labels, filter, null);
        if (rs.first()) {
            ws.setName(rs.getString("WSNAME"));
            ws.setDiscription(rs.getString("WSDESCRIPTION"));
            ws.setImportanceFactor(rs.getDouble("WSIMPORTANCE"));
            ws.setStatus(intToStatus(rs.getInt("WSSTATUS")));
            ws.setType(new WSType(  rs.getInt("WSTID"),
                                    rs.getString("WSTNAME"),
                                    rs.getString("WSTDESCRIPTION"),
                                    rs.getInt("WSTMINIMALSCORE"),
                                    intToStatus(rs.getInt("WSTSTATUS"))));
        }
        conDB.close();
        return ws;
    }

    public static Message searchWorkstationByCustomFilter(String filter) throws SQLException {
        ArrayList<Workstation> search_results = new ArrayList<Workstation>();
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        // define search results schema
        String[] fields = { "workstation.id",
                            "workstation.name",
                            "workstation.description",
                            "workstation.importance",
                            "workstation.status",
                            "wstype.id",
                            "wstype.name",
                            "wstype.description",
                            "wstype.minimalscore",
                            "wstype.status" };
        String[] labels = { "WSID",
                            "WSNAME",
                            "WSDESCRIPTION",
                            "WSIMPORTANCE",
                            "WSSTATUS",
                            "WSTID",
                            "WSTNAME",
                            "WSTDESCRIPTION",
                            "WSTMINIMALSCORE",
                            "WSTSTATUS" };

        // define join keys
        String[] leftKeys = { "workstation.wstypeid" };
        String[] rightKeys = { "wstype.id" };
        
        // run query and process resault-set
        rs = DBConnect.innerJoin(conDB, "workstation", "wstype", leftKeys, rightKeys, fields, labels, filter, null);
        
        // build search results list
        if (rs.isBeforeFirst()) {
            while (rs.next()) {
                search_results.add(new Workstation( rs.getInt("WSID"),
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
        }
        conDB.close();
        return new Message(MessageType.GET_WORKSTATION_ADD_TRIO, search_results);
    }
}

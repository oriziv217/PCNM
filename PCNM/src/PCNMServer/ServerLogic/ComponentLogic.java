package PCNMServer.ServerLogic;

import Entities.Component;
import Entities.Message;
import Entities.MessageType;
import Entities.QuickDic;
import Entities.Status;
import PCNMServer.ServerResources.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class implements application procedures related to PC Components
 * @author Ori Ziv
 */
public class ComponentLogic extends Logic {

    /**
     * This method implements the logic behind search PC Components operation
     * @param mType
     * @param search_model
     * @return
     * @throws SQLException
     */
    public static Message getComponentsWithFilter(MessageType mType, Component search_model) throws SQLException {
        ArrayList<Component> search_results = new ArrayList<Component>();
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        
        // start building the search filter
        String filter = new String();
        boolean isfirst = true;
        // applying filter by component name
        if (search_model.getName() != null) {
            filter = "name LIKE '%" + search_model.getName() + "%'";
            isfirst = false;
        }
        // applying filter by component description
        if (search_model.getDescription() != null) {
            if (isfirst) {
                filter = "description LIKE '%" + search_model.getDescription() + "%'";
                isfirst = false;
            }
            else
                filter = filter + " AND description LIKE '%" + search_model.getDescription() + "%'";
        }
        
        // applying filter by component's price
        if (search_model.getPrice() != 0) {
            float pr = search_model.getPrice();
            if (isfirst) {
                if (pr > 0)
                    filter = "price >= " + pr;
                else
                    filter = "price <= " + pr * (-1);
                isfirst = false;
            } else {
                if (pr > 0)
                    filter = filter + " AND price >= " + pr;
                else
                    filter = filter + " AND price <= " + pr * (-1);
            }
        }
        
        // applying filter by component's value add
        if (search_model.getValueAdd() != 0) {
            float va = search_model.getValueAdd();
            if (isfirst) {
                if (va > 0)
                    filter = "valueAdd >= " + va;
                else
                    filter = "valueAdd <= " + va * (-1);
                isfirst = false;
            } else {
                if (va > 0)
                    filter = filter + " AND valueAdd >= " + va;
                else
                    filter = filter + " AND valueAdd <= " + va * (-1);
            }
        }
        
        // applying filter by status
        if (search_model.getStatus() != Status.Error) {
            if (isfirst) {
                filter = "status = " + statusToInt(search_model.getStatus());
                isfirst = false;
            } else
                filter = filter + " AND status = " + statusToInt(search_model.getStatus());
        }
        
        // run query and process resault-set
        rs = DBConnect.selectWithFilter(conDB, "component", null, filter);
        while (rs.next()) {
            search_results.add(new Component(rs.getInt("ID"),
                                                rs.getString("name"),
                                                rs.getString("description"),
                                                roundFloat(rs.getFloat("price"), 2),
                                                roundFloat(rs.getFloat("valueAdd"), 2),
                                                intToStatus(rs.getInt("status"))));
        }
        return new Message(mType, search_results);
    }
    
    /**
     * This method get all components names and IDs from the DB in order to create quick dictionary on client side
     * @return
     * @throws SQLException
     */
    public static Message createQuickDic() throws SQLException {
        ArrayList<QuickDic> dic = new ArrayList<QuickDic>();
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        String fields = "ID, name";
        rs = DBConnect.selectWithFilter(conDB, "component", fields, null);
        while (rs.next()) {
            dic.add(new QuickDic(rs.getInt("ID"), rs.getString("name")));
        }
        return new Message(MessageType.GET_COMP_QUICKDIC, dic);
    }

    /**
     * This method updates an existing Component record in the DB
     * @param comp
     * @return
     * @throws SQLException
     */
    public static Message updateComponent(Component comp) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        boolean isUpdated;
        String resultString;
        
        // check if comp is installed on a pc
        if (comp.getStatus() == Status.DISABLE) {
            ResultSet rs = DBConnect.selectWithFilter(conDB, "pccomp", "ID", "componentID = '" + comp.getID() + "' "
                    + "AND EndDate = null");
            if (rs.next())
                return new Message (MessageType.UPDATE_COMPONENT, comp, "This component is installed on PC(s)");
        }
        
        // update DB record
        String[] fields = { "id",
                            "name",
                            "description",
                            "price",
                            "valueAdd",
                            "status" };
        String[] values = { Integer.toString(comp.getID()),
                            comp.getName(),
                            comp.getDescription(),
                            String.valueOf(comp.getPrice()),
                            String.valueOf(comp.getValueAdd()),
                            String.valueOf(statusToInt(comp.getStatus())) };
        String[] keyName = { "id" };
        String[] keyVal = { Integer.toString(comp.getID()) };
        isUpdated = DBConnect.updateSingleRecord (conDB, "component", fields, values, keyName, keyVal);
        if (isUpdated) {
            resultString = "OK";
            return new Message (MessageType.UPDATE_COMPONENT, comp, resultString);
        }
        resultString = "Not OK";
        return new Message(MessageType.UPDATE_COMPONENT, null, resultString);
    }

    /**
     * This method adds a new component record to the DB
     * @param comp
     * @return
     * @throws SQLException
     */
    public static Message addComponent(Component comp) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        boolean isAdded;
        String resultString;
        String[] fields = { "name",
                            "description",
                            "price",
                            "valueAdd",
                            "status" };
        String[] values = { comp.getName(),
                            comp.getDescription(),
                            String.valueOf(comp.getPrice()),
                            String.valueOf(comp.getValueAdd()),
                            String.valueOf(statusToInt(comp.getStatus())) };
        isAdded = DBConnect.insertSingleRecord (conDB, "component", fields, values);
        if (isAdded) {
            resultString = "OK";
            rs = DBConnect.selectWithFilter(conDB, "component", null, "name = '" + comp.getName() + "'");
            rs.first();
            comp.setID(rs.getInt("ID"));
            return new Message (MessageType.ADD_COMPONENT, comp, resultString);
        }
        resultString = "Not OK";
        return new Message(MessageType.ADD_COMPONENT, null, resultString);
    }
}

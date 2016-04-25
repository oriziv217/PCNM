package PCNMServer.ServerLogic;

import Entities.Component;
import Entities.Message;
import Entities.MessageType;
import Entities.Status;
import PCNMServer.ServerResources.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ori Ziv
 */
public class ComponentLogic extends Logic {

    public static Message getComponentsWithFilter(Component search_model) throws SQLException {
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
                                                rs.getFloat("price"),
                                                rs.getFloat("valueAdd"),
                                                intToStatus(rs.getInt("status"))));
        }
        return new Message(MessageType.GET_COMP_WITH_FILTER, search_results);
    }
}

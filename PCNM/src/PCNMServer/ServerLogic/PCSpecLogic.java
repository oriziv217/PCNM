package PCNMServer.ServerLogic;

import Entities.Message;
import Entities.MessageType;
import Entities.PCSpec;
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
public class PCSpecLogic extends Logic {

    public static Message getSpecsWithFilter(MessageType msgType, PCSpec search_model) throws SQLException {
        ArrayList<PCSpec> search_results = new ArrayList<PCSpec>();
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        
        // start building the search filter
        String filter = new String();
        boolean isfirst = true;
        // applying filter by specification name
        if (search_model.getName() != null) {
            filter = "name LIKE '%" + search_model.getName() + "%'";
            isfirst = false;
        }
        // applying filter by specification description
        if (search_model.getDescription() != null) {
            if (isfirst) {
                filter = "description LIKE '%" + search_model.getDescription() + "%'";
                isfirst = false;
            }
            else
                filter = filter + " AND description LIKE '%" + search_model.getDescription() + "%'";
        }
        
        // applying filter by specification warrenty
        if (search_model.getWarranty() != 0) {
            int war = search_model.getWarranty();
            if (isfirst) {
                if (war > 0)
                    filter = "warranty >= " + war;
                else
                    filter = "warranty <= " + war * (-1);
                isfirst = false;
            } else {
                if (war > 0)
                    filter = filter + " AND warranty >= " + war;
                else
                    filter = filter + " AND warranty <= " + war * (-1);
            }
        }
        
        // applying filter by specification price
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
        
        // applying filter by specification score
        if (search_model.getScore() != 0) {
            int scr = search_model.getScore();
            if (isfirst) {
                if (scr > 0)
                    filter = "score >= " + scr;
                else
                    filter = "score <= " + scr * (-1);
                isfirst = false;
            } else {
                if (scr > 0)
                    filter = filter + " AND score >= " + scr;
                else
                    filter = filter + " AND score <= " + scr * (-1);
            }
        }
        
        // applying filter by specification status
        if (search_model.getStatus() != Status.Error) {
            if (isfirst) {
                filter = "status = " + statusToInt(search_model.getStatus());
                isfirst = false;
            } else
                filter = filter + " AND status = " + statusToInt(search_model.getStatus());
        }
        
        // run query and process resault-set
        rs = DBConnect.selectWithFilter(conDB, "pcspec", null, filter);
        while (rs.next()) {
            search_results.add(new PCSpec(  rs.getInt("ID"),
                                            rs.getString("name"),
                                            rs.getString("description"),
                                            rs.getInt("warranty"),
                                            roundFloat(rs.getFloat("price"), 2),
                                            rs.getInt("score"),
                                            intToStatus(rs.getInt("status"))));
        }
        return new Message(msgType, search_results);
    }
    
}

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
        conDB.close();
        return new Message(msgType, search_results);
    }

    protected static boolean isUpdateAllowed(PCSpec spec) throws SQLException {
        if (spec.getStatus() != Status.DISABLE) return true;
        
        Connection conDB = DBConnect.mySQLConnection();
        String table = "pc";
        String fields = "COUNT(*) AS LINES_NUM";
        String filter = "PCSpecID = " + spec.getID() + " AND status <> " + statusToInt(Status.DISABLE);
        ResultSet rs = DBConnect.selectWithFilter(conDB, table, fields, filter);

        if (!rs.first()) throw new SQLException("Error reading DB");
        int lines_num = rs.getInt("LINES_NUM");
        conDB.close();
        if (lines_num > 0) return false;
        return true;
    }

    public static Message getAllSpecs() throws SQLException {
        ArrayList<PCSpec> search_results = new ArrayList<PCSpec>();
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;

        // run query and process resault-set
        rs = DBConnect.selectWithFilter(conDB, "pcspec", null, null);
        while (rs.next()) {
            search_results.add(new PCSpec(  rs.getInt("ID"),
                                            rs.getString("name"),
                                            rs.getString("description"),
                                            rs.getInt("warranty"),
                                            roundFloat(rs.getFloat("price"), 2),
                                            rs.getInt("score"),
                                            intToStatus(rs.getInt("status"))));
        }
        conDB.close();
        return new Message(MessageType.Get_ALL_PCSPECS, search_results);
    }

    public static Message addNewSpec(PCSpec newSpec) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        boolean isAdded;
        String[] fields = { "name",
                            "description",
                            "warranty",
                            "price",
                            "score",
                            "status" };
        String[] values = { newSpec.getName(),
                            newSpec.getDescription(),
                            String.valueOf(newSpec.getWarranty()),
                            String.valueOf(newSpec.getPrice()),
                            String.valueOf(newSpec.getScore()),
                            String.valueOf(statusToInt(newSpec.getStatus())) };
        isAdded = DBConnect.insertSingleRecord (conDB, "pcspec", fields, values);
        if (isAdded) {
            rs = DBConnect.selectWithFilter(conDB, "pcspec", null, "name = '" + newSpec.getName() + "'");
            if (!rs.first()) throw new SQLException("Error Reading DB");
            newSpec.setID(rs.getInt("ID"));
            resetScore(0.9, newSpec.getID(), newSpec.getScore());
            conDB.close();
            return getAllSpecs();
        }
        conDB.close();
        return getAllSpecs();
    }

    private static void resetScore(double factor, int id, int score) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        String table = "pcspec";
        String set = "score = score * " + factor;
        String filter = "score < " + score + " AND id <> " + id;
        DBConnect.updateWithFilter(conDB, table, set, filter);
        conDB.close();
    }

    public static Message updateSpec(PCSpec toUpdate) throws SQLException {
        if (isUpdateAllowed(toUpdate)) {
            int sts = statusToInt(toUpdate.getStatus());
            if (sts == 4) throw new SQLException("Invalid input");

            String[] fields = { "ID",
                                "name",
                                "description",
                                "warranty",
                                "price",
                                "score",
                                "status" };
            String[] values = { String.valueOf(toUpdate.getID()),
                                toUpdate.getName(),
                                toUpdate.getDescription(),
                                String.valueOf(toUpdate.getWarranty()),
                                String.valueOf(toUpdate.getPrice()),
                                String.valueOf(toUpdate.getScore()),
                                String.valueOf(sts) };
            String[] keyName = { "id" };
            String[] keyVal = { String.valueOf(toUpdate.getID()) };
            Connection conDB = DBConnect.mySQLConnection();
            boolean isSuccess = DBConnect.updateSingleRecord (conDB, "pcspec", fields, values, keyName, keyVal);
            conDB.close();
            if (isSuccess)
                return getAllSpecs();
            throw new SQLException("Error updating user " + toUpdate.getName());
        }
        throw new SQLException("This PC Specification is installed on an active PC");
    }
}

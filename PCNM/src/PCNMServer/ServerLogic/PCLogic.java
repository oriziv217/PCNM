package PCNMServer.ServerLogic;

import Entities.Component;
import Entities.Message;
import Entities.MessageType;
import Entities.PC;
import Entities.PCSpec;
import PCNMServer.ServerResources.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class implements application procedures related to PCs
 * @author Ori Ziv
 */
public class PCLogic extends Logic {

    /**
     * This method implements the logic behind PC search operation
     * @param search_model
     * @param search_options
     * @return
     * @throws SQLException
     */
    public static Message searchPCByFilter(PC search_model, String search_options) throws SQLException {
        String[] parrsedOptions = search_options.split(",");
        int installDateFilterMode = Integer.parseInt(parrsedOptions[0]);
        int warrentyFilterMode = Integer.parseInt(parrsedOptions[1]);
        ArrayList<PC> search_results = new ArrayList<PC>();
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs, rs1;
        // define search results schema
        String[] fields = {"PC.id",
                            "PC.name",
                            "PC.description",
                            "PC.specInstallation",
                            "PC.status",
                            "PCSPEC.id",
                            "PCSPEC.name",
                            "PCSPEC.description",
                            "PCSPEC.warranty",
                            "PCSPEC.price",
                            "PCSPEC.score",
                            "PCSPEC.status"};
        String[] labels = {"PCID",
                            "PCName",
                            "PCDescription",
                            "PCSpecInstallation",
                            "PCStatus",
                            "PCSpecID",
                            "PCSpecName",
                            "PCSpecDescription",
                            "PCSpecWarrenty",
                            "PCSpecPrice",
                            "PCSpecScore",
                            "PCSpecStatus"};
        
        //define join tables
        String leftTable = "PC";
        String rightTable = "PCSPEC";
        
        // define join keys
        String[] leftKeys = {"PC.PCSpecID"};
        String[] rightKeys = {"PCSPEC.ID"};
        
        // start building the search filter
        String filter = new String();
        boolean isfirst = true;
        
        // filter by name
        if (search_model.getName() != null && !search_model.getName().isEmpty()) {
            filter = "PC.name LIKE '%" + search_model.getName() + "%'";
            isfirst = false;
        }
        // filter by description
        if (search_model.getDescription() != null && !search_model.getDescription().isEmpty()) {
            if (isfirst) {
                filter = "PC.description LIKE '%" + search_model.getDescription() + "%'";
                isfirst = false;
            } else {
                filter = filter + " AND PC.description LIKE '%" + search_model.getDescription() + "%'";
            }
        }
        // filter by installation date
        String installDate = new String();
        if (installDateFilterMode != 0) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            installDate = df.format(search_model.getInstallDate());
        }
        switch (installDateFilterMode) {
            case 1:
                if (isfirst) {
                    filter = "PC.specInstallation >= '" + installDate + "'";
                    isfirst = false;
                } else {
                    filter = filter + " AND PC.specInstallation >= '" + installDate + "'";
                }
                break;
            case 2:
                if (isfirst) {
                    filter = "PC.specInstallation <= '" + installDate + "'";
                    isfirst = false;
                } else {
                    filter = filter + " AND PC.specInstallation <= '" + installDate + "'";
                }
        }
        // filter by status
        int sts = statusToInt(search_model.getStatus());
        if (sts != 4) {
            if (isfirst) {
                filter = "PC.status = " + sts;
                isfirst = false;
            } else {
                filter = filter + " AND PC.status = " + sts;
            }
        }
        // filter by pc-specification
        if (parrsedOptions.length > 2) {
            if (!isfirst)
                filter = filter + " AND ";
            filter = filter + "PC.PCSpecID IN (";
            for (int i = 2 ; i < parrsedOptions.length ; i += 7) {
                if (i > 2)
                    filter = filter + ",";
                filter = filter + "'" + parrsedOptions[i] + "'";
            }
            filter = filter + ")";
        }
        
        // run query on PC JOIN PCSpec tables
        rs = DBConnect.innerJoin(conDB, leftTable, rightTable, leftKeys, rightKeys, fields, labels, filter, null);
        // filter by components
        ArrayList<Component> components = search_model.getComponents();
        ArrayList<Integer> matchedPCs = new ArrayList<Integer>();
        String componentsFilter = "";
        String PCIDFilter = "";
        if (components != null && !components.isEmpty()) {
            componentsFilter = "PCComp.componentID IN (";
            for (int i = 0 ; i < components.size() ; i ++) {
                if (i > 0)
                    componentsFilter = componentsFilter + ",";
                componentsFilter = componentsFilter + "'" + components.get(i).getID() + "'";
            }
            componentsFilter = componentsFilter + ")";
            while (rs.next()) {
                if (PCIDFilter.isEmpty())
                    PCIDFilter = "PCComp.PCID IN ('" + rs.getString("PCID") + "'";
                else
                    PCIDFilter = PCIDFilter + ",'" + rs.getString("PCID") + "'";
            }
            PCIDFilter = PCIDFilter + ")";
            rs1 = DBConnect.selectWithFilter(conDB, "pccomp", "PCID", "EndDate = NULL AND " + componentsFilter + " AND " + PCIDFilter);
            while (rs1.next()) {
                matchedPCs.add(new Integer(rs1.getInt("PCID")));
            }
            rs.beforeFirst();
            while (rs.next()) {
                if (matchedPCs.contains(new Integer(rs.getInt("PCID")))) {
                    search_results.add(new PC(  rs.getInt("PCID"),
                                                rs.getString("PCNAME"),
                                                rs.getString("PCDESCRIPTION"),
                                                new PCSpec( rs.getInt("PCSPECID"),
                                                            rs.getString("PCSPECNAME"),
                                                            rs.getString("PCSPECDESCRIPTION"),
                                                            rs.getInt("PCSPECWARRENTY"),
                                                            rs.getFloat("PCSPECPRICE"),
                                                            rs.getInt("PCSPECSCORE"),
                                                            intToStatus(rs.getInt("PCSPECSTATUS"))),
                                                rs.getTimestamp("PCSpecInstallation"),
                                                intToStatus(rs.getInt("PCSTATUS")),
                                                null));
                }
            }
        } else {
            while (rs.next()) {
                search_results.add(new PC(  rs.getInt("PCID"),
                                            rs.getString("PCNAME"),
                                            rs.getString("PCDESCRIPTION"),
                                            new PCSpec( rs.getInt("PCSPECID"),
                                                        rs.getString("PCSPECNAME"),
                                                        rs.getString("PCSPECDESCRIPTION"),
                                                        rs.getInt("PCSPECWARRENTY"),
                                                        rs.getFloat("PCSPECPRICE"),
                                                        rs.getInt("PCSPECSCORE"),
                                                        intToStatus(rs.getInt("PCSPECSTATUS"))),
                                            rs.getTimestamp("PCSpecInstallation"),
                                            intToStatus(rs.getInt("PCSTATUS")),
                                            null));
            }
        }
        // filter by warrenty expiration
        if (warrentyFilterMode > 0) {
            Calendar today = Calendar.getInstance();
            Calendar expirationTime = Calendar.getInstance();
            for (int i = search_results.size() - 1 ; i >= 0 ; i --) {
                expirationTime.setTime(search_results.get(i).getInstallDate());
                expirationTime.add(Calendar.MONTH, search_results.get(i).getSpec().getWarranty());
                int isExpired = today.compareTo(expirationTime);
                if (isExpired > 0 && warrentyFilterMode == 1) search_results.remove(i);
                if (isExpired < 0 && warrentyFilterMode == 2) search_results.remove(i);
            }
        }
        return new Message(MessageType.PC_SEARCH, search_results);
    }
}

package PCNMServer.ServerLogic;

import Entities.Component;
import Entities.Message;
import Entities.MessageType;
import Entities.PC;
import Entities.PCComp;
import Entities.PCSpec;
import Entities.QuickDic;
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
        ArrayList<PCComp> components = search_model.getInstalledComps();
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
        rs = DBConnect.selectWithFilter(conDB, "pc", fields, null);
        while (rs.next()) {
            dic.add(new QuickDic(rs.getInt("ID"), rs.getString("name")));
        }
        return new Message(MessageType.GET_PC_QUICKDIC, dic);
    }

    /**
     * This method adds a new PC record to the DB
     * @param pc
     * @return
     * @throws SQLException
     */
    public static Message addPC(PC pc) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        boolean isAdded;
        String resultString;
        String[] fields = { "name",
                            "description",
                            "PCSpecID",
                            "SpecInstallation",
                            "status" };
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] values = { pc.getName(),
                            pc.getDescription(),
                            String.valueOf(pc.getSpec().getID()),
                            String.valueOf(df.format(pc.getInstallDate())),
                            String.valueOf(statusToInt(pc.getStatus())) };
        isAdded = DBConnect.insertSingleRecord (conDB, "pc", fields, values);
        if (isAdded) {
            resultString = "OK";
            rs = DBConnect.selectWithFilter(conDB, "pc", null, "name = '" + pc.getName() + "'");
            rs.first();
            pc.setID(rs.getInt("ID"));
            return new Message (MessageType.ADD_PC, pc, resultString);
        }
        resultString = "Not OK";
        return new Message(MessageType.ADD_PC, null, resultString);
    }

    public static Message updatePC(PC pc) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        boolean isUpdated;
        String resultString;
        String[] fields = { "id",
                            "name",
                            "description",
                            "PCSpecID",
                            "SpecInstallation",
                            "status" };
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] values = { Integer.toString(pc.getID()),
                            pc.getName(),
                            pc.getDescription(),
                            Integer.toString(pc.getSpec().getID()),
                            String.valueOf(df.format(pc.getInstallDate())),
                            String.valueOf(statusToInt(pc.getStatus())) };
        String[] keyName = { "id" };
        String[] keyVal = { Integer.toString(pc.getID()) };
        isUpdated = DBConnect.updateSingleRecord (conDB, "pc", fields, values, keyName, keyVal);
        if (isUpdated) {
            resultString = "OK";
            return new Message (MessageType.UPDATE_PC, pc, resultString);
        }
        resultString = "Not OK";
        return new Message(MessageType.UPDATE_PC, null, resultString);
    }

    public static Message getInstPCComp(PC pc_model) throws SQLException {
        ArrayList<PCComp> search_results = new ArrayList<PCComp>();
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        // define search results schema
        String[] fields = { "component.id",
                            "component.name",
                            "component.description",
                            "component.price",
                            "component.valueadd",
                            "pccomp.startdate",
                            "pccomp.enddate",
                            "pccomp.numinstalled",
                            "pccomp.warrenty" };
        String[] labels = { "COMPONENT_ID",
                            "COMPONENT_NAME",
                            "COMPONENT_DESCRIPTION",
                            "COMPONENT_PRICE",
                            "COMPONENT_VALUEADD",
                            "PCCOMP_STARTDATE",
                            "PCCOMP_ENDDATE",
                            "PCCOMP_NUMINSTALLED",
                            "PCCOMP_WARRENTY" };
        
        //define join tables
        String leftTable = "component";
        String rightTable = "pccomp";
        
        // define join keys
        String[] leftKeys = {"component.id"};
        String[] rightKeys = {"pccomp.componentid"};
        
        // start building the search filter
        String filter = "pccomp.pcid = " + pc_model.getID();
        ArrayList<PCComp>cmp_model = pc_model.getInstalledComps();
        if (cmp_model != null && !cmp_model.isEmpty()) {
            Calendar cal = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            cal.setTime(cmp_model.get(0).getEndDate());
            if (cal.after(today))
                filter = filter + " AND pccomp.enddate <> null";
        }

        // run query on COMPONENT JOIN PCCOMP tables
        rs = DBConnect.innerJoin(conDB, leftTable, rightTable, leftKeys, rightKeys, fields, labels, filter, null);
        while (rs.next()) {
            PCComp cmp = new PCComp(rs.getInt("COMPONENT_ID"),
                                    rs.getString("COMPONENT_NAME"),
                                    rs.getString("COMPONENT_DESCRIPTION"),
                                    roundFloat(rs.getFloat("COMPONENT_PRICE"),2),
                                    roundFloat(rs.getFloat("COMPONENT_VALUEADD"), 2),
                                    rs.getTimestamp("PCCOMP_STARTDATE"),
                                    rs.getTimestamp("PCCOMP_ENDDATE"),
                                    rs.getInt("PCCOMP_NUMINSTALLED"),
                                    rs.getInt("PCCOMP_WARRENTY"));
            cmp.setPCID(pc_model.getID());
            search_results.add(cmp);
        }
        return new Message(MessageType.GET_PC_INST_COMP, search_results);
    }
}

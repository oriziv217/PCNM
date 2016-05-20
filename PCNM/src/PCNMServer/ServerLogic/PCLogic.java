package PCNMServer.ServerLogic;

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
        // filter by id
        if (search_model.getID() != 0) {
            if (isfirst) {
                filter = "PC.ID = " + search_model.getID();
                isfirst = false;
            } else {
                filter = filter + " AND PC.ID = " + search_model.getID();
            }
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
            rs1 = DBConnect.selectWithFilter(conDB, "pccomp", "PCID", "EndDate IS NULL AND " + componentsFilter + " AND " + PCIDFilter);
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
        ArrayList<PCComp> installedComponents = new ArrayList<PCComp>();
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
        String filter = "pccomp.pcid = " + pc_model.getID() + " AND pccomp.enddate IS NULL";

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
            installedComponents.add(cmp);
        }
        
        // get PC data
        ArrayList<PC> pc = (ArrayList<PC>)PCLogic.searchPCByFilter(pc_model, "0,0").getEntity();
        if (pc.isEmpty()) throw new SQLException("Could not find specific PC in the DB");
        PC search_result = pc.get(0);
        search_result.setInstalledComponents(installedComponents);
        return new Message(MessageType.GET_PC_INST_COMP, search_result);
    }

    public static Message addRemovePCComp(PC pc) throws SQLException {
        int pcID = pc.getID();
        ArrayList<PCComp> components = pc.getInstalledComps();
        Boolean isSuccess = new Boolean(false);
        
        // case nothing to change
        if (components.isEmpty()) {
            isSuccess = true;
            return new Message(MessageType.CHANGE_PCCOMP, isSuccess);
        }
        
        for (PCComp comp : components) {
            if (comp.getEndDate() == null)
                isSuccess = PCLogic.addPCComp(pcID, comp);
            else
                isSuccess = PCLogic.removePCComp(pcID, comp);
            if (isSuccess == false)
                return new Message(MessageType.CHANGE_PCCOMP, isSuccess);
        }
        return new Message(MessageType.CHANGE_PCCOMP, isSuccess);
    }

    public static boolean addPCComp(int pcID, PCComp comp) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        boolean isAdded = false;
        int componentID = comp.getID();
        
        int numInstalled;
        String filter = "PCID = " + pcID + " AND componentID = " + componentID;
        rs = DBConnect.selectWithFilter(conDB, "pccomp", "MAX(numInstalled) AS Suffix", filter);
        rs.next();
        numInstalled = rs.getInt("Suffix");
        if (rs.wasNull()) numInstalled = 1;
        else numInstalled ++;
        
        String[] fields = { "PCID",
                            "componentID",
                            "startDate",
                            "EndDate",
                            "numInstalled",
                            "Warrenty" };
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] values = { String.valueOf(pcID),
                            String.valueOf(comp.getID()),
                            df.format(comp.getStartDate()),
                            null,
                            String.valueOf(numInstalled),
                            String.valueOf(comp.getWarrenty()) };
        isAdded = DBConnect.insertSingleRecord (conDB, "pccomp", fields, values);
        return isAdded;
    }

    public static boolean removePCComp(int pcID, PCComp comp) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        boolean isUpdated;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] fields = { "EndDate" };
        String[] values = { df.format(comp.getEndDate()) };
        String[] keyName = {"PCID",
                            "componentID",
                            "startDate",
                            "numInstalled" };
        String[] keyVal = { String.valueOf(pcID),
                            String.valueOf(comp.getID()),
                            df.format(comp.getStartDate()),
                            String.valueOf(comp.getNumInstalled()) };
        isUpdated = DBConnect.updateSingleRecord (conDB, "pccomp", fields, values, keyName, keyVal);
        return isUpdated;
    }
}

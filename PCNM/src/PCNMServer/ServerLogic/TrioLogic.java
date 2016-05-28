package PCNMServer.ServerLogic;

import Entities.Message;
import Entities.MessageType;
import Entities.PC;
import Entities.PCSpec;
import Entities.PCUserType;
import Entities.TrioCouple;
import Entities.Workstation;
import PCNMServer.ServerResources.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author ori ziv
 */
public class TrioLogic extends Logic {

    public static Message getActiveTrios() throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        ArrayList<TrioCouple> search_results = new ArrayList<TrioCouple>();
        String[] fields = { "triocoupling.startDate",
                            "PC.ID",
                            "PC.name",
                            "PCSpec.ID",
                            "PCSpec.name",
                            "PCSpec.score",
                            "workstation.ID",
                            "workstation.name",
                            "workstation.importance",
                            "pcusertype.ID",
                            "pcusertype.name",
                            "pcusertype.importance" };
        String[] labels = { "TStart",
                            "PCID",
                            "PCName",
                            "PCSID",
                            "PCSName",
                            "PCSScore",
                            "WID",
                            "WName",
                            "WFactor",
                            "PCUTID",
                            "PCUTName",
                            "PCUTFactor" };
        String fromTable = "triocoupling";
        String[][] joinOns =    {  { "pc", "pc.ID", "triocoupling.PCID" },
                                   { "pcspec", "pcspec.id", "pc.pcspecid" },
                                   { "workstation", "workstation.ID", "triocoupling.workstationID" },
                                   { "pcusertype", "pcusertype.ID", "triocoupling.pcusertypeID" }
                                };
        String filter = "triocoupling.dueDate IS NULL";
        rs = DBConnect.multiJoin(conDB, fields, labels, fromTable, joinOns, filter, null);
        while (rs.next()) {
            PC pc = new PC(rs.getInt("PCID"));
            pc.setName(rs.getString("PCName"));
            PCSpec pcs = new PCSpec(rs.getInt("PCSID"));
            pcs.setName(rs.getString("PCSName"));
            pcs.setScore(rs.getInt("PCSScore"));
            pc.setSpec(pcs);
            PC pc1 = (PC)PCLogic.getInstPCComp(pc).getEntity();
            pc.setInstalledComponents(pc1.getInstalledComps());
            Workstation ws = new Workstation(rs.getInt("WID"));
            ws.setName(rs.getString("WName"));
            ws.setImportanceFactor(roundDouble(rs.getDouble("WFactor"), 2));
            PCUserType pcut = new PCUserType(rs.getInt("PCUTID"));
            pcut.setName(rs.getString("PCUTName"));
            pcut.setImportance(roundDouble(rs.getDouble("PCUTFactor"), 2));
            search_results.add(new TrioCouple(pc, pcut, ws, rs.getTimestamp("TStart")));
        }
        conDB.close();
        return new Message(MessageType.GET_ACTIVE_TRIOS, search_results);
    }

    public static Message getTrioByKey(TrioCouple trio) throws SQLException {
        PC pc = PCLogic.getPCByID(trio.getPCID());
        Workstation ws = WorkstationLogic.getWorkstationByID(trio.getWorkstationID());
        PCUserType pcut = UserTypesLogic.getPCUserTypeByID(trio.getUserTypeID());
        trio.setPc(pc);
        trio.setWorkstation(ws);
        trio.setUserType(pcut);
        return new Message(MessageType.VIEW_TRIO_PROP, trio);
    }

    public static Message addTrio(TrioCouple newTrio) throws SQLException {
        String[] fields = { "PCID",
                            "workstationID",
                            "PCUserTypeID",
                            "startDate",
                            "dueDate" };
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] values = { String.valueOf(newTrio.getPCID()),
                            String.valueOf(newTrio.getWorkstationID()),
                            String.valueOf(newTrio.getUserTypeID()),
                            sdf.format(newTrio.getStartDate()),
                            null };
        
        Connection conDB = DBConnect.mySQLConnection();
        boolean isSuccess = DBConnect.insertSingleRecord(conDB, "triocoupling", fields, values);
        conDB.close();
        if (isSuccess) {
            TrioCouple addedTrio = (TrioCouple)TrioLogic.getTrioByKey(newTrio).getEntity();
            return new Message(MessageType.ADD_TRIO, addedTrio, "OK");
        }
        return new Message(MessageType.ADD_TRIO, newTrio, "NOT OK");
    }

    public static Message UpdateTrio(TrioCouple trio) throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        String resultString;
        String endDate;
        boolean isUpdated;

        String[] fields = { "PCID",
                            "workstationID",
                            "PCUserTypeID",
                            "startDate",
                            "dueDate" };
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (trio.getDueDate() != null)
            endDate = sdf.format(trio.getDueDate());
        else endDate = null;
        String[] values = { String.valueOf(trio.getPCID()),
                            String.valueOf(trio.getWorkstationID()),
                            String.valueOf(trio.getUserTypeID()),
                            sdf.format(trio.getStartDate()),
                            endDate };
        
        String[] keyName = {    "PCID",
                                "workstationID",
                                "PCUserTypeID",
                                "startDate" };
        String[] keyVal = {     String.valueOf(trio.getPCID()),
                                String.valueOf(trio.getWorkstationID()),
                                String.valueOf(trio.getUserTypeID()),
                                sdf.format(trio.getStartDate()) };
        
        isUpdated = DBConnect.updateSingleRecord (conDB, "triocoupling", fields, values, keyName, keyVal);
        if (isUpdated) {
            resultString = "OK";
            conDB.close();
            return new Message (MessageType.END_TRIO_PROP, trio, resultString);
        }
        resultString = "Not OK";
        conDB.close();
        return new Message(MessageType.END_TRIO_PROP, null, resultString);
    }
}

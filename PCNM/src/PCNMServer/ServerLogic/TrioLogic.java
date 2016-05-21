package PCNMServer.ServerLogic;

import Entities.Message;
import PCNMServer.ServerResources.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ori ziv
 */
public class TrioLogic extends Logic {

    public static Message getActiveTrios() throws SQLException {
        Connection conDB = DBConnect.mySQLConnection();
        ResultSet rs;
        String filter = "dueDate IS NULL";
        // TODO: implement this query:
//        SELECT	triocoupling.startDate AS TStart,
//		triocoupling.dueDate AS TDue,
//		PC.ID AS PCID,
//		PC.name AS PCName,
//		PCSpec.ID AS PCSID,
//		PCSpec.name AS PCSName,
//		PCSpec.score AS PCSScore,
//		workstation.ID AS WID,
//		workstation.name AS WName,
//		workstation.importance AS WFactor,
//		pcusertype.ID AS PCUTID,
//		pcusertype.name AS PCUTName,
//		pcusertype.importance AS PCUTFactor
//        FROM	triocoupling
//        JOIN	pc
//        ON      pc.ID = triocoupling.PCID
//        JOIN	pcspec
//        ON	pcspec.id = pc.pcspecid
//        JOIN	workstation
//        ON	workstation.ID = triocoupling.workstationID
//        JOIN	pcusertype
//        ON	pcusertype.ID = triocoupling.pcusertypeID
//        WHERE	triocoupling.dueDate IS NULL;
        return null;
    }
    
}

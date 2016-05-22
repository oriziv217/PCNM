package PCNMClient.PCNMClientController;

import Entities.TrioCouple;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.TrioSCR;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author ori ziv
 */
public class TrioCTRL extends CTRL {

    private static ArrayList<TrioCouple> activeTrios;
    
    public static void openTrioScreen(ArrayList<TrioCouple> activTrios) {
        TrioCTRL.activeTrios = activTrios;
        ArrayList<String[]> trio_tbl = new ArrayList<String[]>();
        for (TrioCouple tc : activTrios)
            trio_tbl.add(activeTrioToStrings(tc));
        PCNMClientStart.appWindow.setTitle("PCNM - Active PC-Workstation-User Type Connections");
        PCNMClientStart.switchPanels(new TrioSCR(trio_tbl));
    }

    private static String[] activeTrioToStrings(TrioCouple tc) {
        if (tc == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String[] tcStrings = {  sdf.format(tc.getStartDate()),
                                Integer.toString(tc.getPCID()),
                                tc.getPc().getName(),
                                Integer.toString(tc.getPc().getSpec().getID()),
                                tc.getPc().getSpec().getName(),
                                Integer.toString(tc.getPc().getSpec().getScore()),
                                Integer.toString(tc.getWorkstationID()),
                                tc.getWorkstation().getName(),
                                Double.toString(tc.getWorkstation().getImportanceFactor()),
                                Integer.toString(tc.getUserTypeID()),
                                tc.getUserType().getName(),
                                Double.toString(tc.getUserType().getImportance()),
                                Float.toString(tc.getTotal_score()) };
        return tcStrings;
    }
}

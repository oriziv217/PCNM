package PCNMClient.PCNMClientController;

import Entities.TrioCouple;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.NetMapSCR;
import PCNMClient.PCNMClientView.TrioSCR;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ori ziv
 */
public class TrioCTRL extends CTRL {

    private static int fieldFilterMode;
    private static String fieldFilterString;
    private static int startDateFilterMode;
    private static Date startDateFilter;
    private static int scoreFilterMode;
    private static float scoreFilter;
    
    private static ArrayList<TrioCouple> activeTrios;

    public static int getFieldFilterMode() {
        return fieldFilterMode;
    }

    public static void setFieldFilterMode(int fieldFilterMode) {
        TrioCTRL.fieldFilterMode = fieldFilterMode;
    }

    public static  String getFieldFilterString() {
        return fieldFilterString;
    }

    public static void setFieldFilterString(String fieldFilterString) {
        TrioCTRL.fieldFilterString = fieldFilterString;
    }

    public static int getStartDateFilterMode() {
        return startDateFilterMode;
    }

    public static void setStartDateFilterMode(int startDateFilterMode) {
        TrioCTRL.startDateFilterMode = startDateFilterMode;
    }

    public static Date getStartDateFilter() {
        return startDateFilter;
    }

    public static void setStartDateFilter(Date startDateFilter) {
        TrioCTRL.startDateFilter = startDateFilter;
    }

    public static int getScoreFilterMode() {
        return scoreFilterMode;
    }

    public static void setScoreFilterMode(int scoreFilterMode) {
        TrioCTRL.scoreFilterMode = scoreFilterMode;
    }

    public static float getScoreFilter() {
        return scoreFilter;
    }

    public static void setScoreFilter(float scoreFilter) {
        TrioCTRL.scoreFilter = scoreFilter;
    }
    
    public static void openTrioScreen(ArrayList<TrioCouple> activTrios) {
        TrioCTRL.activeTrios = activTrios;
        ArrayList<String[]> trio_tbl = new ArrayList<String[]>();
        for (TrioCouple tc : activTrios)
            trio_tbl.add(activeTrioToStrings(tc));
        PCNMClientStart.appWindow.setTitle("PCNM - Active PC-Workstation-User Type Connections");
        PCNMClientStart.switchPanels(new TrioSCR(trio_tbl));
    }
    
    public static void closeBtnPressed() {
        PCNMClientStart.appWindow.setTitle("PCNM - Network Mapping");
        PCNMClientStart.switchPanels(new NetMapSCR());
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

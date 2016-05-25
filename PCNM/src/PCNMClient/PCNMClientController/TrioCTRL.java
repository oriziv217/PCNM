package PCNMClient.PCNMClientController;

import Entities.Message;
import Entities.MessageType;
import Entities.PC;
import Entities.PCComp;
import Entities.TrioCouple;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.NetMapSCR;
import PCNMClient.PCNMClientView.TrioPropertiesSCR;
import PCNMClient.PCNMClientView.TrioSCR;
import java.io.IOException;
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

    public static void viewTrioProperties(Date sDate, int PCID, int WSID, int PCUTID) throws IOException {
        TrioCouple trio = new TrioCouple(PCID, WSID, PCUTID, sDate);
        PCNMClientModel.sendMessageToServer(new Message(MessageType.VIEW_TRIO_PROP, trio));
    }
    
    public static void addNewTrio() throws IOException {
        PCNMClientModel.sendMessageToServer(new Message(MessageType.GET_DATA_ADD_TRIO));
    }
    
    public static void endTrio(Date sDate, int PCID, int WSID, int PCUTID, Date eDate) throws IOException {
        TrioCouple trio = new TrioCouple(PCID, WSID, PCUTID, sDate, eDate);
        PCNMClientModel.sendMessageToServer(new Message(MessageType.END_TRIO_PROP, trio));
    }

    public static void openTrioPropertiesScreen(TrioCouple trioCouple) {
        PC pc = trioCouple.getPc();
        ArrayList<PCComp> comps = pc.getInstalledComps();
        ArrayList<String[]> compStrings = new ArrayList<String[]>();
        for (PCComp cmp : comps)
            compStrings.add(cmp.toString().split(","));
        pc.setInstalledComponents(null);
        String[] pcStrings = pc.toString().split(",");
        String[] wsStrings = trioCouple.getWorkstation().toString().split(",");
        String[] pcutStrings = trioCouple.getUserType().toString().split(",");
        
        PCNMClientStart.appWindow.setTitle("PCNM - PC-Workstation-User Type Connection Properties");
        PCNMClientStart.switchPanels(new TrioPropertiesSCR(pcStrings, compStrings, wsStrings, pcutStrings));
    }

    public static void closeBtnPressedPropertiesScreen() {
        ArrayList<String[]> trio_tbl = new ArrayList<String[]>();
        for (TrioCouple tc : TrioCTRL.activeTrios)
            trio_tbl.add(activeTrioToStrings(tc));
        PCNMClientStart.appWindow.setTitle("PCNM - Active PC-Workstation-User Type Connections");
        PCNMClientStart.switchPanels(new TrioSCR(trio_tbl));
    }
}

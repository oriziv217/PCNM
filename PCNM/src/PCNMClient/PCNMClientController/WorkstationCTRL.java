package PCNMClient.PCNMClientController;

import Entities.Message;
import Entities.MessageType;
import Entities.QuickDic;
import Entities.Status;
import Entities.WSType;
import Entities.Workstation;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.NetMapSCR;
import PCNMClient.PCNMClientView.WSTypeSCR;
import PCNMClient.PCNMClientView.WorkStationSearchResaults;
import PCNMClient.PCNMClientView.WorkstationSCR;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This calls implements Workstation Screen controllers
 * @author ori ziv
 */
public class WorkstationCTRL extends CTRL {

    private static ArrayList<Workstation> workstation_pull;
    private static ArrayList<QuickDic> wsQD;
    
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new NetMapSCR());
    }

    public static void openWorkstationWindow(ArrayList<WSType> wstypes) {
        PCNMClientStart.cur_ent.setWstypes(wstypes);
        PCNMClientStart.switchPanels(new WorkstationSCR());
    }

    public static void searchBtnPressed(String name, String description, String importance, String type, String status) throws IOException {
        double imp;
        WSType wst;
        Status sts;
        
        PCNMClientStart.workstationSearchFilters.clear();
        PCNMClientStart.workstationSearchFilters.add(name);
        PCNMClientStart.workstationSearchFilters.add(description);
        PCNMClientStart.workstationSearchFilters.add(importance);
        PCNMClientStart.workstationSearchFilters.add(type);
        PCNMClientStart.workstationSearchFilters.add(status);
        
        if (type.equals("All Types"))
            wst = null;
        else
            wst = new WSType(type);
        
        if (importance.equals("Greater then 1"))
            imp = 1.0;
        else if (importance.equals("Less then 1"))
            imp = -1.0;
        else
            imp = 0;
        
        if (status.equals("Enabled"))
            sts = Status.ENABLE;
        else if (status.equals("Disabled"))
            sts = Status.DISABLE;
        else if (status.equals("Suspended"))
            sts = Status.SUSPENDED;
        else
            sts = null;
        
        Workstation ws = new Workstation(name, description, imp, sts, wst);
        PCNMClientModel.sendMessageToServer(new Message(MessageType.GET_WORKSTATIOS_WITH_FILTER, ws));
    }

    public static void processSearchResults(ArrayList<Workstation> search_result) {
        PCNMClientStart.cur_ent.setWorkstations(search_result);
        workstation_pull = PCNMClientStart.cur_ent.getWorkstations();
        ArrayList<String>ws_tbl = new ArrayList<String>();
        for (Workstation ws : workstation_pull) {
            ws_tbl.add(ws.toString());
        }
        PCNMClientStart.switchPanels(new WorkStationSearchResaults(ws_tbl));
    }

    public static boolean isNameUnique(int ID, String name) throws IOException {
        if (wsQD == null) throw new IOException("Can't verify new workstation");
        for (QuickDic qd : wsQD)
            if (qd.getID() != ID && qd.getFirstVal().equalsIgnoreCase(name))
                return false;
        return true;
    }

    public static void setWSDic(ArrayList<QuickDic> dic) {
        wsQD = dic;
    }

    public static void AddWorkstationBtnPressed(String name, String description, double importance, String status,
                                                String typeID, String typeName, String typeDescription, String typeMinScore, String typeStatus) throws IOException {
        Status sts, typSts;
        if (status.equals("Enabled")) sts = Status.ENABLE;
        else if (status.equals("Disabled")) sts = Status.DISABLE;
        else if (status.equals("Suspended")) sts = Status.SUSPENDED;
        else sts = Status.Error;
        if (typeStatus.equals("Enabled")) typSts = Status.ENABLE;
        else if (typeStatus.equals("Disabled")) typSts = Status.DISABLE;
        else if (typeStatus.equals("Suspended")) typSts = Status.SUSPENDED;
        else typSts = Status.Error;
        PCNMClientModel.sendMessageToServer(new Message(MessageType.ADD_WORKSTATION,
                                            new Workstation(name,
                                                            description,
                                                            importance,
                                                            sts,
                                                            new WSType(Integer.parseInt(typeID),
                                                                        typeName,
                                                                        typeDescription,
                                                                        Integer.parseInt(typeMinScore),
                                                                        typSts))));
    }

    public static void refreshWorkstationWindow(MessageType msgType, Workstation ws) {
        ArrayList<String>ws_tbl = new ArrayList<String>();
        if (msgType == MessageType.ADD_WORKSTATION) {
            PCNMClientStart.cur_ent.addToWorkstations(ws);
            wsQD.add(new QuickDic(ws.getID(), ws.getName()));
        } else if (msgType == MessageType.UPDATE_WORKSTATION) {
            PCNMClientStart.cur_ent.updateWorkstations(ws);
            for (QuickDic qd : wsQD)
                if (qd.getID() == ws.getID()) {
                    String[] vals = {ws.getName()};
                    qd.setVals(vals);
                }
                    
        }
        workstation_pull = PCNMClientStart.cur_ent.getWorkstations();
        for (Workstation row : workstation_pull)
            ws_tbl.add(row.toString());
        PCNMClientStart.switchPanels(new WorkStationSearchResaults(ws_tbl));
    }

    public static void searchResaultCloseBtnPressed() {
        PCNMClientStart.switchPanels(new WorkstationSCR());
    }

    public static void UpdateWorkstationBtnPressed(int id, String name, String description, double importance, String status,
                            String typeID, String typeName, String typeDescription, String typeMinScore, String typeStatus) throws IOException {
        Status sts, typSts;
        if (status.equals("Enabled")) sts = Status.ENABLE;
        else if (status.equals("Disabled")) sts = Status.DISABLE;
        else if (status.equals("Suspended")) sts = Status.SUSPENDED;
        else sts = Status.Error;
        if (typeStatus.equals("Enabled")) typSts = Status.ENABLE;
        else if (typeStatus.equals("Disabled")) typSts = Status.DISABLE;
        else if (typeStatus.equals("Suspended")) typSts = Status.SUSPENDED;
        else typSts = Status.Error;
        
        PCNMClientModel.sendMessageToServer(new Message(MessageType.UPDATE_WORKSTATION,
                                            new Workstation(id,
                                                            name,
                                                            description,
                                                            importance,
                                                            sts,
                                                            new WSType(Integer.parseInt(typeID),
                                                                        typeName,
                                                                        typeDescription,
                                                                        Integer.parseInt(typeMinScore),
                                                                        typSts))));
    }

    public static void manageTypesBtnPressed() throws IOException {
        PCNMClientModel.sendMessageToServer(new Message(MessageType.MANAGE_WSTYPES));
    }

    public static void openWSTypeMngScreen(ArrayList<WSType> types_tbl) {
        ArrayList<String> scr_tbl = new ArrayList<String>();
        for (WSType typ : types_tbl)
            scr_tbl.add(typ.toString());
        PCNMClientStart.switchPanels(new WSTypeSCR(scr_tbl));
    }

    public static void AddWSTypeOKBtnPressed(String name, String description, int min_rate, String status) throws IOException {
        Status sts, typSts;
        if (status.equals("Enabled")) sts = Status.ENABLE;
        else if (status.equals("Disabled")) sts = Status.DISABLE;
        else if (status.equals("Suspended")) sts = Status.SUSPENDED;
        else sts = Status.Error;
        
        PCNMClientModel.sendMessageToServer(new Message(MessageType.ADD_WSTYPE, new WSType (name, description, min_rate, sts)));
    }

    public static void UpdateWSTypeOKBtnPressed(int ID, String name, String description, int min_rate, String status) throws IOException {
        Status sts, typSts;
        if (status.equals("Enabled")) sts = Status.ENABLE;
        else if (status.equals("Disabled")) sts = Status.DISABLE;
        else if (status.equals("Suspended")) sts = Status.SUSPENDED;
        else sts = Status.Error;
                
        PCNMClientModel.sendMessageToServer(new Message(MessageType.UPDATE_WSTYPE, new WSType (ID, name, description, min_rate, sts)));
    }
}

package PCNMClient.PCNMClientController;

import Entities.Message;
import Entities.MessageType;
import Entities.Status;
import Entities.WSType;
import Entities.Workstation;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.NetMapSCR;
import PCNMClient.PCNMClientView.WorkstationSCR;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This calls implements Workstation Screen controllers
 * @author ori ziv
 */
public class WorkstationCTRL extends CTRL {

    private static ArrayList<Workstation> workstation_pull;
    
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
        
        if (type.equals("All Types"))
            wst = null;
        else
            wst = new WSType(name);
        
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
        workstation_pull = search_result;
        ArrayList<String>ws_tbl = new ArrayList<String>();
        for (Workstation ws : search_result) {
            ws_tbl.add(ws.toString());
        }
        PCNMClientStart.switchPanels(new WorkstationSCR(ws_tbl));
    }
}

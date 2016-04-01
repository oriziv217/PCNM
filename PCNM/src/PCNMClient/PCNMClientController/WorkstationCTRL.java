package PCNMClient.PCNMClientController;

import Entities.WSType;
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
    
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new NetMapSCR());
    }

    public static void openWorkstationWindow(ArrayList<WSType> wstypes) {
        PCNMClientStart.cur_ent.setWstypes(wstypes);
        PCNMClientStart.switchPanels(new WorkstationSCR());
    }

    public static void searchBtnPressed(String name, String description, String importance, String type, String status) throws IOException {
        
    }
}

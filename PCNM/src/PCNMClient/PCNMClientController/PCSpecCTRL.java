package PCNMClient.PCNMClientController;

import Entities.Message;
import Entities.MessageType;
import Entities.PCSpec;
import Entities.Status;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.PCSPecSCR;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ori ziv
 */
public class PCSpecCTRL extends CTRL {

    public static void openPCSPecSCR(ArrayList<PCSpec> allSpecs) {
        ArrayList<String[]> scr_tbl = new ArrayList<String[]>();
        PCNMClientStart.cur_ent.setPCSpecs(allSpecs);
        for (PCSpec pcs : allSpecs)
            scr_tbl.add(pcs.toString().split(","));
        PCNMClientStart.switchPanels(new PCSPecSCR(scr_tbl));
    }
    
    public static void closeBtnPressed() {
        PCNMClientStart.appWindow.setTitle("PCNM - PC Search Screen");
        PCCTRL.openPCSCR();
    }

    public static void AddPCSpecOKBtnPressed(String name, String description, float price, int warranty, int score, String status) throws IOException {
        PCSpec newSpec = new PCSpec(name, description, warranty, price, score, stringToStatus(status));
        PCNMClientModel.sendMessageToServer(new Message(MessageType.ADD_PCSPEC, newSpec));
    }

    public static void UpdatePCSpecOKBtnPressed(int ID, String name, String description, float price, int warranty, int score, String status) throws IOException {
        PCSpec updateSpec = new PCSpec(ID, name, description, warranty, price, score, stringToStatus(status));
        PCNMClientModel.sendMessageToServer(new Message(MessageType.UPDATE_PCSPEC, updateSpec));
    }
}

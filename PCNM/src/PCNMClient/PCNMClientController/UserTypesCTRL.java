package PCNMClient.PCNMClientController;

import Entities.PCUserType;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.NetMapSCR;
import java.util.ArrayList;

/**
 * this class implements PC-User-Types screen controls
 * @author Ori Ziv
 */
public class UserTypesCTRL extends CTRL {
    
    /**
     *
     * @param query_results
     * @return
     */
    public static ArrayList<String> processGetUserTypesResponse(ArrayList<PCUserType> query_results) {
        ArrayList<String> search_results = new ArrayList<String>();
        for (PCUserType typ : query_results)
            search_results.add(typ.toString());
        return search_results;
    }
    
    /**
     *
     */
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new NetMapSCR());
    }
}

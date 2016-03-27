package PCNMClient.PCNMClientController;

import Entities.PCUserType;
import java.util.ArrayList;

/**
 * this class implements PC-User-Types screen controls
 * @author Ori Ziv
 */
public class UserTypesCTRL extends CTRL {
    public static ArrayList<String> processGetUserTypesResponse(ArrayList<PCUserType> query_results) {
        ArrayList<String> search_results = new ArrayList<String>();
        for (PCUserType typ : query_results)
            search_results.add(query_results.toString());
        return search_results;
    }
}

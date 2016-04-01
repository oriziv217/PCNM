package PCNMClient;

import Entities.WSType;
import java.util.ArrayList;

/**
 * This class implements the current entities used by the client
 * @author ori ziv
 */
public class CurrentEntities {
    private ArrayList<WSType> wstypes;

    public CurrentEntities() {
        wstypes = new ArrayList<WSType>();
    }

    public ArrayList<WSType> getWstypes() {
        return wstypes;
    }

    public void setWstypes(ArrayList<WSType> wstypes) {
        this.wstypes = wstypes;
    }
    
    public ArrayList<String> getStringWstypes() {
        ArrayList<String> wstStr = new ArrayList<String>();
        for (WSType row : wstypes)
            wstStr.add(row.toString());
        return wstStr;
    }
}

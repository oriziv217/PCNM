package PCNMClient;

import Entities.WSType;
import Entities.Workstation;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class implements the current entities used by the client
 * @author ori ziv
 */
public class CurrentEntities {
    private ArrayList<WSType> wstypes;
    private ArrayList<Workstation> workstations;

    public CurrentEntities() {
        wstypes = new ArrayList<WSType>();
        workstations = new ArrayList<Workstation>();
    }

    public ArrayList<WSType> getWstypes() {
        return wstypes;
    }

    public ArrayList<Workstation> getWorkstations() {
        return workstations;
    }
    
    public void setWstypes(ArrayList<WSType> wstypes) {
        this.wstypes = wstypes;
    }
    
    public void setWorkstations(ArrayList<Workstation> workstations) {
        this.workstations = workstations;
        Collections.sort(this.workstations);
    }
    
    public void addToWorkstations(Workstation ws) {
        if (workstations.isEmpty()) {
            workstations.add(ws);
            return;
        }
        int comperator;
        for (int i = 0 ; i < workstations.size() ; i ++) {
            comperator = workstations.get(i).compareTo(ws);
            if (comperator > 0)
                workstations.add(i, ws);
        }
    }
    
    public void updateWorkstations(Workstation ws) {
        int index = Collections.binarySearch(workstations, ws);
        workstations.remove(index);
        workstations.add(index, ws);
    }
    
    public ArrayList<String> getStringWstypes() {
        ArrayList<String> wstStr = new ArrayList<String>();
        for (WSType row : wstypes)
            wstStr.add(row.toString());
        return wstStr;
    }
}

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
    
    public void addToWstypes (WSType wst) {
        wstypes.add(wst);
    }
    
    public int wstypeIndexByID (int ID) {
        for (int i = 0 ; i < wstypes.size() ; i ++)
            if (ID == wstypes.get(i).getID())
                return i;
        return -1;
    }
    
    public int wstypeIndexByName (String name) {
        for (int i = 0 ; i < wstypes.size() ; i ++)
            if (name.equals(wstypes.get(i).getName()))
                return i;
        return -1;
    }
    
    public boolean wstypeUpdate (WSType wst) {
        int index = wstypeIndexByID(wst.getID());
        if (index == -1) return false;
        wstypes.remove(index);
        wstypes.add(wst);
        return true;
    }
    
    public void addToWorkstations(Workstation ws) {
        // case list is empty
        if (workstations.isEmpty()) {
            workstations.add(ws);
            return;
        }
        // case add to head of the list
        int comperator = workstations.get(0).compareTo(ws);
        if (comperator > 0) {
            workstations.add(0, ws);
            return;
        }
        // case add to the middle of the list
        for (int i = 0 ; i < workstations.size() ; i ++) {
            comperator = workstations.get(i).compareTo(ws);
            if (comperator > 0) {
                workstations.add(i, ws);
                return;
            }
        }
        // case add to list's tail
        workstations.add(workstations.size(), ws);
    }
    
    public void updateWorkstations(Workstation ws) {
        int index = Collections.binarySearch(workstations, ws);
        workstations.remove(index);
        addToWorkstations(ws);
    }
    
    public ArrayList<String> wstypesToString() {
        ArrayList<String> wstStr = new ArrayList<String>();
        for (WSType row : wstypes)
            wstStr.add(row.toString());
        return wstStr;
    }
}

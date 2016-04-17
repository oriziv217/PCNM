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

    /**
     * Default constructor
     */
    public CurrentEntities() {
        wstypes = new ArrayList<WSType>();
        workstations = new ArrayList<Workstation>();
    }

    /**
     * current workstation types getter
     * @return
     */
    public ArrayList<WSType> getWstypes() {
        return wstypes;
    }

    /**
     * current workstations getter
     * @return
     */
    public ArrayList<Workstation> getWorkstations() {
        return workstations;
    }
    
    /**
     * current workstation types setter
     * @param wstypes
     */
    public void setWstypes(ArrayList<WSType> wstypes) {
        this.wstypes = wstypes;
    }
    
    /**
     * current workstation setter + sorting
     * @param workstations
     */
    public void setWorkstations(ArrayList<Workstation> workstations) {
        this.workstations = workstations;
        Collections.sort(this.workstations);
    }
    
    /**
     * add workstation type to current array
     * @param wst
     */
    public void addToWstypes (WSType wst) {
        wstypes.add(wst);
    }
    
    /**
     * get the index of a specific workstation type (by ID)
     * if no match return -1
     * @param ID
     * @return
     */
    public int wstypeIndexByID (int ID) {
        for (int i = 0 ; i < wstypes.size() ; i ++)
            if (ID == wstypes.get(i).getID())
                return i;
        return -1;
    }
    
    /**
     * return the index of the first occurrence of workstation type with a specific name
     * if no occurrence found return -1
     * @param name
     * @return
     */
    public int wstypeIndexByName (String name) {
        for (int i = 0 ; i < wstypes.size() ; i ++)
            if (name.equals(wstypes.get(i).getName()))
                return i;
        return -1;
    }
    
    /**
     * update workstation type with a specific ID
     * return true if updated
     * @param wst
     * @return
     */
    public boolean wstypeUpdate (WSType wst) {
        int index = wstypeIndexByID(wst.getID());
        if (index == -1) return false;
        wstypes.remove(index);
        wstypes.add(wst);
        return true;
    }
    
    /**
     * add workstation to sorted current workstations array
     * @param ws
     */
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
    
    /**
     * update workstation with a specific ID
     * @param ws
     */
    public void updateWorkstations(Workstation ws) {
        int index = Collections.binarySearch(workstations, ws);
        workstations.remove(index);
        addToWorkstations(ws);
    }
    
    /**
     * return an array of comma separated representation of current workstation types
     * @return
     */
    public ArrayList<String> wstypesToString() {
        ArrayList<String> wstStr = new ArrayList<String>();
        for (WSType row : wstypes)
            wstStr.add(row.toString());
        return wstStr;
    }
}

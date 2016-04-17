package Entities;

import java.io.Serializable;

/**
 * This class implements a quick generic dictionary
 * The idea is to create a quick reference in order
 * to identify tuples duplications without re-querying the DB
 * @author ori ziv
 */
public class QuickDic implements Serializable , Comparable<QuickDic>{
   private final int ID;
   private String[] vals;
   
    /**
     * Default constructor
     */
    public QuickDic () {
       ID = -1;
       vals = null;
   }
   
    /**
     * ID only constructor
     * @param ID
     */
    public QuickDic (int ID) {
       this.ID = ID;
   }
   
    /**
     * Single value constructor
     * @param ID
     * @param val
     */
    public QuickDic (int ID, String val) {
       this.ID = ID;
       vals = new String[1];
       vals[0] = val;
   }
   
    /**
     * Full constructor (array of values)
     * @param ID
     * @param vals
     */
    public QuickDic (int ID, String[] vals) {
       this.ID = ID;
       this.vals = vals;
   }
   
    /**
     * Vals array setter
     * @param vals
     */
    public void setVals(String[] vals) { this.vals = vals; }

    /**
     * Set specific val at a specific position
     * @param val
     * @param index
     */
    public void setValsAt(String val, int index) {
       if (vals == null || index >= vals.length) return;
       vals[index] = val;
   }

    /**
     * Add val to the end of vals[]
     * @param val
     */
    public void addVal(String val) {
       if (vals == null) {
           vals = new String[1];
           vals[0] = val;
           return;
       }
       String[] newVals = new String[vals.length + 1];
       for (int i = 0 ; i <vals.length ; i ++)
           newVals[i] = vals[i];
       newVals[newVals.length - 1] = val;
       setVals(newVals);
   }
   
    /**
     * Get all vals (as an array)
     * @return
     */
    public String[] getVals() { return vals; }

    /**
     * get a specific val from vals[] or null if index not exists
     * @param index
     * @return
     */
    public String getValAt(int index) {
       if (index >= vals.length) return null;
       return vals[index];
   }

    /**
     * get val[0] or null if vals[] is empty
     * @return
     */
    public String getFirstVal() {
       if (vals == null) return null;
       return vals[0];
   }

    /**
     * ID getter
     * @return
     */
    public int getID() { return ID; }
   
   @Override
    public int compareTo(QuickDic comparedTo) {
        return this.ID - comparedTo.getID();
    }
}

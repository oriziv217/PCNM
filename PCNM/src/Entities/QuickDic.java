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
   
   public QuickDic () {
       ID = -1;
       vals = null;
   }
   
   public QuickDic (int ID) {
       this.ID = ID;
   }
   
   public QuickDic (int ID, String val) {
       this.ID = ID;
       vals = new String[1];
       vals[0] = val;
   }
   
   public QuickDic (int ID, String[] vals) {
       this.ID = ID;
       this.vals = vals;
   }
   
   public void setVals(String[] vals) { this.vals = vals; }
   public void setValsAt(String val, int index) {
       if (vals == null || index >= vals.length) return;
       vals[index] = val;
   }
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
   
   public String[] getVals() { return vals; }
   public String getValAt(int index) {
       if (index >= vals.length) return null;
       return vals[index];
   }
   public String getFirstVal() {
       if (vals == null) return null;
       return vals[0];
   }

   public int getID() { return ID; }
   
   @Override
    public int compareTo(QuickDic comparedTo) {
        return this.ID - comparedTo.getID();
    }
}

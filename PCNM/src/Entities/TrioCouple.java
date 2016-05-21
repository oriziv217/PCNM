package Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ori ziv
 */
public class TrioCouple implements Serializable, Comparable<TrioCouple> {
    private int PCID;
    private int userTypeID;
    private int workstationID;
    private PC pc;
    private PCUserType userType;
    private Workstation workstation;
    private Date startDate;
    private Date dueDate;
    private float total_score;

    public TrioCouple(int PCID, int userTypeID, int workstationID, Date startDate) {
        this.PCID = PCID;
        this.pc = new PC(PCID);
        this.userTypeID = userTypeID;
        this.userType = new PCUserType(userTypeID);
        this.workstationID = workstationID;
        this.workstation = new Workstation(workstationID);
        this.startDate = startDate;
    }

    public TrioCouple(int PCID, int userTypeID, int workstationID, Date startDate, Date dueDate) {
        this(PCID, userTypeID, workstationID, startDate);
        this.dueDate = dueDate;
    }

    public TrioCouple(PC pc, PCUserType userType, Workstation workstation, Date startDate) {
        this.pc = pc;
        this.PCID = pc.getID();
        this.userType = userType;
        this.userTypeID = userType.getID();
        this.workstation = workstation;
        this.workstationID = userType.getID();
        this.startDate = startDate;
        calcTotalScore();
    }

    public TrioCouple(PC pc, PCUserType userType, Workstation workstation, Date startDate, Date dueDate) {
        this(pc, userType, workstation, startDate);
        this.dueDate = dueDate;
    }

    public TrioCouple(TrioCouple tc) {
        this.PCID = tc.getPCID();
        this.userTypeID = tc.getUserTypeID();
        this.workstationID = tc.getWorkstationID();
        this.pc = tc.getPc();
        this.userType = tc.getUserType();
        this.workstation = tc.getWorkstation();
        this.startDate = tc.getStartDate();
        this.dueDate = tc.getDueDate();
        this.total_score = tc.getTotal_score();
    }

    public int getPCID() {
        return PCID;
    }

    public void setPCID(int PCID) {
        this.PCID = PCID;
    }

    public int getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(int userTypeID) {
        this.userTypeID = userTypeID;
    }

    public int getWorkstationID() {
        return workstationID;
    }

    public void setWorkstationID(int workstationID) {
        this.workstationID = workstationID;
    }

    public PC getPc() {
        return pc;
    }

    public void setPc(PC pc) {
        this.pc = pc;
        calcTotalScore();
    }

    public PCUserType getUserType() {
        return userType;
    }

    public void setUserType(PCUserType userType) {
        this.userType = userType;
        calcTotalScore();
    }

    public Workstation getWorkstation() {
        return workstation;
    }

    public void setWorkstation(Workstation workstation) {
        this.workstation = workstation;
        calcTotalScore();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public float getTotal_score() {
        return total_score;
    }

    private void calcTotalScore() {
        if (pc.getSpec() == null) return;
        total_score = pc.getSpec().getScore();
        ArrayList<PCComp> components = pc.getInstalledComps();
        if (components != null && !components.isEmpty()) {
            for (PCComp comp : components) {
                total_score *= comp.getValueAdd();
                if (total_score == 0) return;
            }
        }
        
        total_score = (float) workstation.getImportanceFactor();
        if (total_score == 0) return;
        
        total_score *= (float) userType.getImportance();
        if (total_score == 0) return;
        total_score = roundFloat(total_score, 2);
    }

    private float roundFloat(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    @Override
    public int compareTo(TrioCouple comparedTo) {
        float gap = this.total_score - comparedTo.getTotal_score();
        return Math.round(gap);
    }
    
    public String[] toStrings() {
        String[] trioStrings = new String[4];
        trioStrings[0] = pc.toString();
        trioStrings[1] = workstation.toString();
        trioStrings[2] = userType.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String sDate = sdf.format(startDate);
        String dDate;
        if (dueDate == null) dDate = "";
        else dDate = sdf.format(dueDate);
        trioStrings[3] = sDate + "," + dDate + "," + total_score;
        return trioStrings;
    }
}

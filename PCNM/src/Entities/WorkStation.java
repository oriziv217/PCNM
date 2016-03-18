/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package Entities;

import java.io.Serializable;

/**
 *This class represents a record in WorkStations table
 * @author Sivan Yehuda
 */
public class WorkStation implements Serializable{
    private String ID;
    private String name;
    private String discription;
    private float importanceFactor;
    private boolean status;
    private String pcID;
    private String pcutID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public float getImportanceFactor() {
        return importanceFactor;
    }

    public void setImportanceFactor(float importanceFactor) {
        this.importanceFactor = importanceFactor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPcID() {
        return pcID;
    }

    public void setPcID(String pcID) {
        this.pcID = pcID;
    }

    public String getPcutID() {
        return pcutID;
    }

    public void setPcutID(String pcutID) {
        this.pcutID = pcutID;
    }

    public WorkStation(String ID, String name, String discription, float importanceFactor, boolean status, String pcID, String pcutID) {
        this.ID = ID;
        this.name = name;
        this.discription = discription;
        this.importanceFactor = importanceFactor;
        this.status = status;
        this.pcID = pcID;
        this.pcutID = pcutID;
    }
}


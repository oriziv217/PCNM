/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package Entities;

import java.io.Serializable;

/**
 *
 * @author Sivan Yehuda
 * @author Ori Ziv
 */
public class WSType implements Serializable {
    private int ID;
    private String name;
    private String description;
    private int minimalScore;
    private Status status;

    public WSType () {
        ID = 0;
        name = "";
        description = "";
        minimalScore = 0;
        status = Status.Error;
    }

    public WSType (int ID) {
        this();
        this.ID = ID;
    }
    public WSType (String name) {
        this();
        this.name = name;
    }
    
    public WSType(String name, String description, int minimalScore, Status status) {
        ID = 0;
        this.name = name;
        this.description = description;
        this.minimalScore = minimalScore;
        this.status = status;
    }

    public WSType(int ID, String name, String description, int minimalScore, Status status) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.minimalScore = minimalScore;
        this.status = status;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinimalScore() {
        return minimalScore;
    }

    public void setMinimalScore(int minimalScore) {
        this.minimalScore = minimalScore;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    /**
     * 
     * @return
     */
    @Override
    public String toString() {
        String sts = "Error";
        
        switch (this.status) {
            case ENABLE:
                sts = "Enabled";
                break;
            case DISABLE:
                sts = "Disabled";
                break;
            case SUSPENDED:
                sts = "Suspended";
                break;
        }
        
        return String.valueOf(ID) + "," + name + "," + description + "," + String.valueOf(minimalScore) + "," + sts;
    }
}

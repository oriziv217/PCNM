package Entities;

import java.io.Serializable;

/**
 * This class represents a record in Workstation type (WSType) table
 * @author Sivan Yehuda
 * @author Ori Ziv
 */
public class WSType implements Serializable {
    private int ID;
    private String name;
    private String description;
    private int minimalScore;
    private Status status;

    /**
     * Default constructor
     */
    public WSType () {
        ID = 0;
        name = "";
        description = "";
        minimalScore = 0;
        status = Status.Error;
    }

    /**
     * Creates an empty WSType with significant ID
     * @param ID
     */
    public WSType (int ID) {
        this();
        this.ID = ID;
    }

    /**
     * Creates an empty WSType with significant name
     * @param name
     */
    public WSType (String name) {
        this();
        this.name = name;
    }
    
    /**
     * All but ID constructor - used for new WSType
     * @param name
     * @param description
     * @param minimalScore
     * @param status
     */
    public WSType(String name, String description, int minimalScore, Status status) {
        ID = 0;
        this.name = name;
        this.description = description;
        this.minimalScore = minimalScore;
        this.status = status;
    }

    /**
     * Full constructor
     * @param ID
     * @param name
     * @param description
     * @param minimalScore
     * @param status
     */
    public WSType(int ID, String name, String description, int minimalScore, Status status) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.minimalScore = minimalScore;
        this.status = status;
    }
    
    /**
     * ID getter
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     * ID setter
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Name getter
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Description getter
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Description setter
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Minimal score getter
     * @return
     */
    public int getMinimalScore() {
        return minimalScore;
    }

    /**
     * Minimal score setter
     * @param minimalScore
     */
    public void setMinimalScore(int minimalScore) {
        this.minimalScore = minimalScore;
    }

    /**
     * Status getter
     * @return
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Status setter
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
    /**
     * Overriding toString: return a comma separated representer
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

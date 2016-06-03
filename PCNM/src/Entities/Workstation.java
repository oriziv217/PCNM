package Entities;

import java.io.Serializable;

/**
 * This class represents a record in Workstations table
 * @author Sivan Yehuda
 * @author Ori Ziv
 */
public class Workstation implements Serializable, Comparable<Workstation> {
    private int ID;
    private String name;
    private String description;
    private double importanceFactor;
    private Status status;
    private WSType type;

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
    public String getDiscription() {
        return description;
    }

    /**
     * Description setter
     * @param description
     */
    public void setDiscription(String description) {
        this.description = description;
    }

    /**
     * Importance factor getter
     * @return
     */
    public double getImportanceFactor() {
        return importanceFactor;
    }

    /**
     * Importance factor getter
     * @param importanceFactor
     */
    public void setImportanceFactor(double importanceFactor) {
        this.importanceFactor = importanceFactor;
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
     * Workstation type getter
     * @return
     */
    public WSType getType() {
        return type;
    }

    /**
     * Workstation type setter
     * @param type
     */
    public void setType(WSType type) {
        this.type = type;
    }
    
    /**
     * Default constructor
     */
    public Workstation() {
        this.ID = 0;
        this.name = "";
        this.description = "";
        this.importanceFactor = 0;
        this.status = Status.Error;
        this.type = null;
    }

    public Workstation(int ID) {
        this();
        this.ID = ID;
    }
    
    public Workstation (int ID, String name) {
        this();
        this.ID = ID;
        this.name = name;
    }
    /**
     * No ID and no type constructor - used for new Workstations and for search filters
     * @param name
     * @param description
     * @param importanceFactor
     * @param status
     */
    public Workstation(String name, String description, double importanceFactor, Status status) {
        this.name = name;
        this.description = description;
        this.importanceFactor = importanceFactor;
        this.status = status;
    }

    /**
     * No ID constructor - used for new Workstations and for search filters
     * @param name
     * @param description
     * @param importanceFactor
     * @param status
     * @param type
     */
    public Workstation(String name, String description, double importanceFactor, Status status, WSType type) {
        this.name = name;
        this.description = description;
        this.importanceFactor = importanceFactor;
        this.status = status;
        this.type = type;
    }

    /**
     * Full constructor
     * @param ID
     * @param name
     * @param description
     * @param importanceFactor
     * @param status
     * @param type
     */
    public Workstation(int ID, String name, String description, double importanceFactor, Status status, WSType type) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.importanceFactor = importanceFactor;
        this.status = status;
        this.type = type;
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
        return String.valueOf(ID) + ","
                + name + ","
                + description + ","
                + String.valueOf(importanceFactor) + ","
                + sts + ","
                + type.toString();
    }

    @Override
    public int compareTo(Workstation comparedTo) {
        return this.ID - comparedTo.getID();
    }
}


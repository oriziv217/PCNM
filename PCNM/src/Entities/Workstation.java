/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package Entities;

import java.io.Serializable;

/**
 *This class represents a record in Workstations table
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

    public String getDiscription() {
        return description;
    }

    public void setDiscription(String description) {
        this.description = description;
    }

    public double getImportanceFactor() {
        return importanceFactor;
    }

    public void setImportanceFactor(double importanceFactor) {
        this.importanceFactor = importanceFactor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public WSType getType() {
        return type;
    }

    public void setType(WSType type) {
        this.type = type;
    }
    
    public Workstation() {
    }

    public Workstation(String name, String description, double importanceFactor, Status status) {
        this.name = name;
        this.description = description;
        this.importanceFactor = importanceFactor;
        this.status = status;
    }

    public Workstation(String name, String description, double importanceFactor, Status status, WSType type) {
        this.name = name;
        this.description = description;
        this.importanceFactor = importanceFactor;
        this.status = status;
        this.type = type;
    }

    public Workstation(int ID, String name, String description, double importanceFactor, Status status, WSType type) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.importanceFactor = importanceFactor;
        this.status = status;
        this.type = type;
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


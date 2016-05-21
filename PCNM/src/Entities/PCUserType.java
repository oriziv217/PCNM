package Entities;

import java.io.Serializable;

/**
 * This class represents a record in PCUserType table
 * @author ori ziv
 */
public class PCUserType implements Serializable {
    private int ID;
    private String name;
    private String description;
    private double importance;
    private Status status;

    // Constructors

    /**
     * Default Constructor
     */
    public PCUserType() {
        this.ID = 0;
        this.name = "";
        this.description = "";
        this.importance = 0;
        this.status = Status.Error;
    }
    
    public PCUserType(int ID) {
        this();
        this.ID = ID;
    }
    
    /**
     *
     * @param name
     */
    public PCUserType(String name) {
        this.name = name;
    }
    
    /**
     *
     * @param name
     * @param description
     * @param importance
     * @param status
     */
    public PCUserType(String name, String description, double importance, Status status) {
        this.name = name;
        this.description = description;
        this.importance = importance;
        this.status = status;
    }

    /**
     *
     * @param ID
     * @param name
     * @param description
     * @param importance
     * @param status
     */
    public PCUserType(int ID, String name, String description, double importance, Status status) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.importance = importance;
        this.status = status;
    }

    /**
     *
     * @param pCUserType
     */
    public PCUserType (PCUserType pCUserType) {
        this.ID = pCUserType.getID();
        this.name = pCUserType.getName();
        this.importance = pCUserType.getImportance();
        this.status = pCUserType.getStatus();
        this.description = pCUserType.getDescription();
    }
    // Setters and Getters

    /**
     *
     * @param ID
     */
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param importance
     */
    public void setImportance(double importance) {
        this.importance = importance;
    }

    /**
     *
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public double getImportance() {
        return importance;
    }

    /**
     *
     * @return
     */
    public Status getStatus() {
        return status;
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
        return String.valueOf(ID) + "," + name + "," + description + "," + String.valueOf(importance) + "," + sts;
    }
}

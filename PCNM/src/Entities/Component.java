package Entities;
import java.io.Serializable;

/**
 * This class represents a record in Component table
 * @author Ori Ziv
 * @author Sivan Yehuda
 */
public class Component implements Serializable, Comparable<Component> {
    private int ID;
    private String name;
    private String description;
    private int price;
    private float valueAdd;
    private Status status;

    /**
     * Default constructor
     */
    public Component () {
        this.ID = 0;
        this.name = "";
        this.description = "";
        this.price = 0;
        this.valueAdd = 1;
        this.status = Status.Error;
    }

    /**
     * No ID field constructor - used for new Components
     * @param name
     * @param description
     * @param price
     * @param valueAdd
     * @param status
     */
    public Component(String name, String description, int price, float valueAdd, Status status) {
        this.ID = 0;
        this.name = name;
        this.description = description;
        this.price = price;
        this.valueAdd = valueAdd;
        this.status = status;
    }

    /**
     * Full Constructor
     * @param ID
     * @param name
     * @param description
     * @param price
     * @param valueAdd
     * @param status
     */
    public Component(int ID, String name, String description, int price, float valueAdd, Status status) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.valueAdd = valueAdd;
        this.status = status;
    }

    /**
     * Clone constructor
     * @param comp
     */
    public Component(Component comp) {
        this.ID = comp.getID();
        this.name = comp.getName();
        this.description = comp.getDescription();
        this.price = comp.getPrice();
        this.valueAdd = comp.getValueAdd();
        this.status = comp.getStatus();
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
     * Price getter
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     * Price setter
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Value Add getter
     * @return
     */
    public float getValueAdd() {
        return valueAdd;
    }

    /**
     * Value add setter
     * @param valueAdd
     */
    public void setValueAdd(float valueAdd) {
        this.valueAdd = valueAdd;
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
        
        return ID + "," + name + "," + description + "," + price + "," + valueAdd + "," + sts;
    }

    @Override
    public int compareTo(Component comparedTo) {
       return this.ID - comparedTo.getID(); 
    }
}

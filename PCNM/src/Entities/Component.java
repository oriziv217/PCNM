package Entities;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents a record in Component table
 * @author Ori Ziv
 * @author Sivan Yehuda
 */
public class Component implements Serializable, Comparable<Component> {
    private int ID;
    private String name;
    private String description;
    private float price;
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
    public Component(String name, String description, float price, float valueAdd, Status status) {
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
    public Component(int ID, String name, String description, float price, float valueAdd, Status status) {
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

    Component(int ID) {
        this();
        this.ID = ID;
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
    public float getPrice() {
        return price;
    }

    /**
     * Price setter
     * @param price
     */
    public void setPrice(float price) {
        this.price = roundFloat(price, 2);
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
        this.valueAdd = roundFloat(valueAdd, 2);
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
    
    private float roundFloat(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}

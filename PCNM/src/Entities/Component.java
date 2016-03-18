/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package Entities;
import java.util.Date;
import java.io.Serializable;
/**
 *
 * @author Sivan Yehuda
 */
public class Component {
    private String ID;
    private String name;
    private String description;
    private Date arrivalDate;
    private boolean status;
    private float valueAdd;

    public Component(String ID, String name, String description, Date arrivalDate, boolean status, float valueAdd) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.arrivalDate = arrivalDate;
        this.status = status;
        this.valueAdd = valueAdd;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getValueAdd() {
        return valueAdd;
    }

    public void setValueAdd(float valueAdd) {
        this.valueAdd = valueAdd;
    }
}

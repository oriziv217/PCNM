package Entities;
import java.io.Serializable;

/**
 *
 * @author Sivan Yehuda
 */
public class PC {
    private String ID;
    private String name;
    private float rate;
    private boolean status;
    private String wsID;
    private String pcutID;
    private String description;

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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getWsID() {
        return wsID;
    }

    public void setWsID(String wsID) {
        this.wsID = wsID;
    }

    public String getPcutID() {
        return pcutID;
    }

    public void setPcutID(String pcutID) {
        this.pcutID = pcutID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPcSpecID() {
        return pcSpecID;
    }

    public void setPcSpecID(String pcSpecID) {
        this.pcSpecID = pcSpecID;
    }

    public PC(String ID, String name, float rate, boolean status, String wsID, String pcutID, String description, String pcSpecID) {
        this.ID = ID;
        this.name = name;
        this.rate = rate;
        this.status = status;
        this.wsID = wsID;
        this.pcutID = pcutID;
        this.description = description;
        this.pcSpecID = pcSpecID;
    }
    private String pcSpecID;
}

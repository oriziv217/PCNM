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
public class PCComponent {
    private String pcID;
    private String componentID;
    private Date from;

    public PCComponent(String pcID, String componentID, Date from, Date due) {
        this.pcID = pcID;
        this.componentID = componentID;
        this.from = from;
        this.due = due;
    }
    private Date due;

    public String getPcID() {
        return pcID;
    }

    public void setPcID(String pcID) {
        this.pcID = pcID;
    }

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }
}

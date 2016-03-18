/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package Entities;

/**
 *
 * @author Sivan Yehuda
 */
public class WSType {
    private String ID;
    private String name;
    private boolean status;

    public WSType(String ID, String name, boolean status) {
        this.ID = ID;
        this.name = name;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

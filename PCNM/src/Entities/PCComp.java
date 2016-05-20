package Entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ori ziv
 */
public class PCComp implements Serializable {
    int PCID;
    int ID;
    String name;
    String description;
    float price;
    float valueAdd;
    Date startDate;
    Date endDate;
    int numInstalled;
    int warrenty;

    public PCComp() {
        PCID = 0;
        ID = 0;
        name = "";
        description = "";
        price = 0;
        valueAdd = 0;
        startDate = null;
        endDate = null;
        numInstalled = 0;
        warrenty = 0;
    }

    public PCComp(int ID, Date startDate) {
        this();
        this.ID = ID;
        this.startDate = startDate;
    }

    public PCComp(int ID, Date startDate, Date endDate) {
        this(ID, startDate);
        this.endDate = endDate;
    }

    public PCComp (int ID, Date startDate, Date endDate, int numInstalled) {
        this(ID, startDate, endDate);
        this.numInstalled = numInstalled;
    }
    
    public PCComp (int ID, Date startDate, Date endDate, int numInstalled, int warrenty) {
        this(ID, startDate, endDate, numInstalled);
        this.warrenty = warrenty;
    }
    
    public PCComp(int ID, String name, String description, float price, float valueAdd, Date startDate, Date endDate, int numInstalled, int warrenty) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.valueAdd = valueAdd;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numInstalled = numInstalled;
        this.warrenty = warrenty;
    }

    public int getPCID() {
        return PCID;
    }
    
    public void setPCID(int PCID) {
        this.PCID = PCID;
    }
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getValueAdd() {
        return valueAdd;
    }

    public void setValueAdd(float valueAdd) {
        this.valueAdd = valueAdd;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumInstalled() {
        return numInstalled;
    }

    public void setNumInstalled(int numInstalled) {
        this.numInstalled = numInstalled;
    }

    public int getWarrenty() {
        return warrenty;
    }

    public void setWarrenty(int warrenty) {
        this.warrenty = warrenty;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String sDate, eDate;
        if (startDate == null) sDate = "";
        else sDate = df.format(startDate);
        if (endDate == null) eDate = "";
        else eDate = df.format(endDate);
        return ID + "," + name + "," + description + "," + price + "," + valueAdd + "," + sDate + "," + eDate + "," + numInstalled + "," + warrenty;
    }
}

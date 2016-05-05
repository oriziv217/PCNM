package Entities;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Ori Ziv
 * @author Sivan Yehuda
 */
public class PC implements Serializable, Comparable<PC> {
    private int ID;
    private String name;
    private String description;
    private PCSpec spec;
    private Date installDate;
    private Status status;
    private ArrayList<Component> components;

    public PC() {
        this.ID = 0;
        this.name = "";
        this.description = "";
        this.spec = null;
        this.installDate = null;
        this.status = Status.Error;
        this.components = new ArrayList<Component>();
    }

    public PC(String name, String description, PCSpec spec, Date installDate, Status status, ArrayList<Component> components) {
        this.ID = 0;
        this.name = name;
        this.description = description;
        this.spec = spec;
        this.installDate = installDate;
        this.status = status;
        this.components = components;
    }

    public PC(int ID, String name, String description, PCSpec spec, Date installDate, Status status, ArrayList<Component> components) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.spec = spec;
        this.installDate = installDate;
        this.status = status;
        this.components = components;
    }
    
    public PC (PC pc) {
        this(pc.getID(), pc.getName(), pc.getDescription(), pc.getSpec(), pc.getInstallDate(), pc.getStatus(), pc.getComponents());
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

    public PCSpec getSpec() {
        return spec;
    }

    public void setSpec(PCSpec spec) {
        this.spec = spec;
    }

    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public Component getComponentByID(int cmpID) {
        if (components == null || components.isEmpty()) return null;
        for (Component cmp : components)
            if (cmp.getID() == cmpID)
                return cmp;
        return null;
    }
    
    public Component getComponentByName (String cmpName) {
        if (components == null || components.isEmpty()) return null;
        for (Component cmp : components)
            if (cmp.getName().equals(cmpName))
                return cmp;
        return null;
    }
    
    public void setComponent (Component cmp) {
       int index = getComponentIndex(cmp.getID());
       if (index != -1)
           components.remove(index);
       components.add(cmp);
    }
    
    public boolean removeComponentByID (int cmpID) {
        int index = getComponentIndex(ID);
        if (index == -1) return false;
        components.remove(index);
        return true;
    }
    
    /**
     * Overriding toString: return a comma separated representer
     * @return
     */
    @Override
    public String toString() {
        String pc = name + "," + description;
        switch (this.status) {
            case ENABLE:
                pc = pc + ",Enabled";
                break;
            case DISABLE:
                pc = pc + ",Disabled";
                break;
            case SUSPENDED:
                pc = pc + ",Suspended";
                break;
            case Error:
                pc = pc + ",Error";
                break;
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (installDate == null) pc = pc + ",";
        else pc = pc + "," + df.format(installDate);
        pc = pc + "," + spec.toString();
        if (components == null) pc = pc + ",";
        else
            for (Component cmp : components)
                pc = pc + "," + cmp.toString();
        return pc;
    }
    
    @Override
    public int compareTo(PC comparedTo) {
        return this.ID - comparedTo.getID();
    }

    private int getComponentIndex(int id) {
        if (components == null || components.isEmpty()) return -1;
        for (int i = 0 ; i < components.size() ; i ++)
            if (id == components.get(i).getID())
                return i;
        return -1;
    }
}

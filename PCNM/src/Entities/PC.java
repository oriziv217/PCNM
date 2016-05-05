package Entities;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class represents a record in PC table with its relations with PC-Specifications and Components
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

    /**
     * Default Constructor
     */
    public PC() {
        this.ID = 0;
        this.name = "";
        this.description = "";
        this.spec = null;
        this.installDate = null;
        this.status = Status.Error;
        this.components = new ArrayList<Component>();
    }

    /**
     * All but ID constructor - mostly used for search and new PC records
     * @param name
     * @param description
     * @param spec
     * @param installDate
     * @param status
     * @param components
     */
    public PC(String name, String description, PCSpec spec, Date installDate, Status status, ArrayList<Component> components) {
        this.ID = 0;
        this.name = name;
        this.description = description;
        this.spec = spec;
        this.installDate = installDate;
        this.status = status;
        this.components = components;
    }

    /**
     * Full constructor
     * @param ID
     * @param name
     * @param description
     * @param spec
     * @param installDate
     * @param status
     * @param components
     */
    public PC(int ID, String name, String description, PCSpec spec, Date installDate, Status status, ArrayList<Component> components) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.spec = spec;
        this.installDate = installDate;
        this.status = status;
        this.components = components;
    }
    
    /**
     * Copy constructor
     * @param pc
     */
    public PC (PC pc) {
        this(pc.getID(), pc.getName(), pc.getDescription(), pc.getSpec(), pc.getInstallDate(), pc.getStatus(), pc.getComponents());
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
     * PC Specification getter
     * @return
     */
    public PCSpec getSpec() {
        return spec;
    }

    /**
     * PC Specification setter
     * @param spec
     */
    public void setSpec(PCSpec spec) {
        this.spec = spec;
    }

    /**
     * Installation date getter
     * @return
     */
    public Date getInstallDate() {
        return installDate;
    }

    /**
     * Installation date setter
     * @param installDate
     */
    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    /**
     * PC status getter
     * @return
     */
    public Status getStatus() {
        return status;
    }

    /**
     * PC status setter
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Components list getter
     * @return
     */
    public ArrayList<Component> getComponents() {
        return components;
    }

    /**
     * Components list setter
     * @param components
     */
    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    /**
     * Get specific component from PC's components list by its ID
     * @param cmpID
     * @return
     */
    public Component getComponentByID(int cmpID) {
        if (components == null || components.isEmpty()) return null;
        for (Component cmp : components)
            if (cmp.getID() == cmpID)
                return cmp;
        return null;
    }
    
    /**
     * Get specific component from PC's components list by its name
     * @param cmpName
     * @return
     */
    public Component getComponentByName (String cmpName) {
        if (components == null || components.isEmpty()) return null;
        for (Component cmp : components)
            if (cmp.getName().equals(cmpName))
                return cmp;
        return null;
    }
    
    /**
     * Add a component to this PCs components list
     * @param cmp
     */
    public void setComponent (Component cmp) {
       int index = getComponentIndex(cmp.getID());
       if (index != -1)
           components.remove(index);
       components.add(cmp);
    }
    
    /**
     * Removes a specific component from this PC's components list by its ID
     * @param cmpID
     * @return
     */
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

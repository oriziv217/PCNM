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
    private ArrayList<PCComp> installedComps;

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
        this.installedComps = new ArrayList<PCComp>();
    }

    /**
     *
     * @param ID
     */
    public PC(int ID) {
        this();
        this.ID = ID;
    }
    
    /**
     * All but ID constructor - mostly used for search and new PC records
     * @param name
     * @param description
     * @param spec
     * @param installDate
     * @param status
     * @param pccomps
     */
    public PC(String name, String description, PCSpec spec, Date installDate, Status status, ArrayList<PCComp> pccomps) {
        this.ID = 0;
        this.name = name;
        this.description = description;
        this.spec = spec;
        this.installDate = installDate;
        this.status = status;
        this.installedComps = pccomps;
    }

    /**
     * Full constructor
     * @param ID
     * @param name
     * @param description
     * @param spec
     * @param installDate
     * @param status
     * @param pccomps
     */
    public PC(int ID, String name, String description, PCSpec spec, Date installDate, Status status, ArrayList<PCComp> pccomps) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.spec = spec;
        this.installDate = installDate;
        this.status = status;
        this.installedComps = pccomps;
    }
    
    /**
     * Copy constructor
     * @param pc
     */
    public PC (PC pc) {
        this(pc.getID(), pc.getName(), pc.getDescription(), pc.getSpec(), pc.getInstallDate(), pc.getStatus(), pc.getInstalledComps());
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
    public ArrayList<PCComp> getInstalledComps() {
        return installedComps;
    }

    /**
     * Components list setter
     * @param pccomps
     */
    public void setInstalledComponents(ArrayList<PCComp> pccomps) {
        this.installedComps = pccomps;
    }

    /**
     * Get specific component from PC's components list by its ID
     * @param cmpID
     * @return
     */
    public PCComp getComponentByID(int cmpID) {
        if (installedComps == null || installedComps.isEmpty()) return null;
        for (PCComp cmp : installedComps)
            if (cmp.getID() == cmpID)
                return cmp;
        return null;
    }
    
    /**
     * Get specific component from PC's components list by its name
     * @param cmpName
     * @return
     */
    public PCComp getComponentByName (String cmpName) {
        if (installedComps == null || installedComps.isEmpty()) return null;
        for (PCComp cmp : installedComps)
            if (cmp.getName().equals(cmpName))
                return cmp;
        return null;
    }
    
    /**
     * Add a component to this PCs components list
     * @param cmp
     */
    public void setInstalledComps (PCComp cmp) {
       int index = getInstalledComponentIndex(cmp.getID());
       if (index != -1)
           installedComps.remove(index);
       installedComps.add(cmp);
    }
    
    /**
     * Removes a specific component from this PC's components list by its ID
     * @param cmpID
     * @return
     */
    public boolean removeComponentByID (int cmpID) {
        int index = getInstalledComponentIndex(ID);
        if (index == -1) return false;
        installedComps.remove(index);
        return true;
    }
    
    /**
     * Overriding toString: return a comma separated representer
     * @return
     */
    @Override
    public String toString() {
        String pc = ID + "," + name + "," + description;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (installDate == null) pc = pc + ",";
        else pc = pc + "," + df.format(installDate);
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
        pc = pc + "," + spec.toString();
        if (installedComps == null || installedComps.isEmpty()) pc = pc + ",";
        else
            for (PCComp cmp : installedComps)
                pc = pc + "," + cmp.toString();
        return pc;
    }
    
    @Override
    public int compareTo(PC comparedTo) {
        return this.ID - comparedTo.getID();
    }

    private int getInstalledComponentIndex(int id) {
        if (installedComps == null || installedComps.isEmpty()) return -1;
        for (int i = 0 ; i < installedComps.size() ; i ++)
            if (id == installedComps.get(i).getID())
                return i;
        return -1;
    }
}

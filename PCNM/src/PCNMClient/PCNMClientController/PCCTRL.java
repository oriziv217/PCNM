package PCNMClient.PCNMClientController;

import Entities.Component;
import Entities.Message;
import Entities.MessageType;
import Entities.PC;
import Entities.PCSpec;
import Entities.Status;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.NetMapSCR;
import PCNMClient.PCNMClientView.PCCompSCR;
import PCNMClient.PCNMClientView.PCSCR;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ori Ziv
 */
public class PCCTRL extends CTRL {
    private static String nameFilter;
    private static String descriptionFilter;
    private static int installationDateModeFilter;
    private static Date instalationDateFilter;
    private static int warrentyModeFilter;
    private static int statusFilter;
    private static boolean[] selectedComponentsFilter;
    private static boolean[] selectedSpecsFilter;
    private static ArrayList<Component> enaComp;
    private static ArrayList<PCSpec> enaSpec;

    public static String getNameFilter() {
        return nameFilter;
    }

    public static void setNameFilter(String nameFilter) {
        PCCTRL.nameFilter = nameFilter;
    }

    public static String getDescriptionFilter() {
        return descriptionFilter;
    }

    public static void setDescriptionFilter(String descriptionFilter) {
        PCCTRL.descriptionFilter = descriptionFilter;
    }

    public static int getInstallationDateModeFilter() {
        return installationDateModeFilter;
    }

    public static void setInstallationDateModeFilter(int installationDateModeFilter) {
        PCCTRL.installationDateModeFilter = installationDateModeFilter;
    }

    public static Date getInstalationDateFilter() {
        return instalationDateFilter;
    }

    public static void setInstalationDateFilter(Date instalationDateFilter) {
        PCCTRL.instalationDateFilter = instalationDateFilter;
    }

    public static int getWarrentyModeFilter() {
        return warrentyModeFilter;
    }

    public static void setWarrentyModeFilter(int warrentyModeFilter) {
        PCCTRL.warrentyModeFilter = warrentyModeFilter;
    }

    public static int getStatusFilter() {
        return statusFilter;
    }

    public static void setStatusFilter(int statusFilter) {
        PCCTRL.statusFilter = statusFilter;
    }

    public static boolean[] getSelectedComponentsFilter() {
        return PCCTRL.selectedComponentsFilter;
    }
    
    public static void setSelectedComponentsFilter (boolean[] selectedComponentsFilter) {
        PCCTRL.selectedComponentsFilter = selectedComponentsFilter;
    }
    
    public static boolean[] getSelectedSpecsFilter() {
        return PCCTRL.selectedSpecsFilter;
    }
    
    public static void setSelectedSpecsFilter (boolean[] selectedSpecsFilter) {
        PCCTRL.selectedSpecsFilter = selectedSpecsFilter;
    }
    
    public static void setEnaComp(ArrayList<Component> enaComp) {
        PCCTRL.enaComp = enaComp;
    }
    
    private static ArrayList<Component> getEnaComp() {
        return enaComp;
    }

    public static ArrayList<PCSpec> getEnaSpec() {
        return enaSpec;
    }

    public static void setEnaSpec(ArrayList<PCSpec> enaSpec) {
        PCCTRL.enaSpec = enaSpec;
    }
    
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new NetMapSCR());
    }

    public static void searchBtnPressed() throws IOException {
        // define a search model to search PCs
        PC search_model = new PC();
        if (nameFilter != null && !nameFilter.isEmpty())
            search_model.setName(nameFilter);
        if (descriptionFilter != null && !descriptionFilter.isEmpty())
            search_model.setDescription(descriptionFilter);
        if (instalationDateFilter != null)
            search_model.setInstallDate(instalationDateFilter);
        switch (statusFilter) {
            case 1:
                search_model.setStatus(Status.ENABLE);
                break;
            case 2:
                search_model.setStatus(Status.DISABLE);
                break;
            case 3:
                search_model.setStatus(Status.SUSPENDED);
                break;
        }
        if (selectedComponentsFilter != null)
            for (int i = 0 ; i < selectedComponentsFilter.length ; i ++)
                if (selectedComponentsFilter[i])
                    search_model.setComponent(enaComp.get(i));
        
        // define search model options
        String search_options = installationDateModeFilter + "," + warrentyModeFilter;
        if (selectedSpecsFilter != null)
            for (int i = 0 ; i < selectedSpecsFilter.length ; i ++)
                if (selectedSpecsFilter[i])
                    search_options = search_options + "," + enaSpec.get(i).toString();
        PCNMClientModel.sendMessageToServer(new Message(MessageType.PC_SEARCH, search_model, search_options));
    }

    public static void openPCCompSCRBtnPressed() {
        PCNMClientStart.switchPanels(new PCCompSCR());
    }

    public static void openPCSCR() {
        if (enaComp == null || enaSpec == null) {
            PCNMClientStart.switchPanels(new PCSCR());
            return;
        }
        ArrayList<String>compList = new ArrayList<String>();
        ArrayList<String>specList = new ArrayList<String>();
        for (Component cmp : enaComp)
            compList.add(cmp.toString());
        for (PCSpec spec : enaSpec)
            specList.add(spec.toString());
        PCNMClientStart.switchPanels(new PCSCR(compList, specList));
    }

    public static void openPCSpecSCRBtnPressed() {
        
    }
}

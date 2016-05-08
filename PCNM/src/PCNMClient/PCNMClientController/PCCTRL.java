package PCNMClient.PCNMClientController;

import Entities.Component;
import Entities.Message;
import Entities.MessageType;
import Entities.PC;
import Entities.PCSpec;
import Entities.QuickDic;
import Entities.Status;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.NetMapSCR;
import PCNMClient.PCNMClientView.PCCompSCR;
import PCNMClient.PCNMClientView.PCSCR;
import PCNMClient.PCNMClientView.PCSearchResultSCR;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class implements the PC management screens controllers
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
    private static ArrayList<PC> pc_pull;
    private static ArrayList<QuickDic> pcQD;

    /**
     * search screen filter getter
     * @return
     */
    public static String getNameFilter() {
        return nameFilter;
    }

    /**
     * search screen filter setter
     * @param nameFilter
     */
    public static void setNameFilter(String nameFilter) {
        PCCTRL.nameFilter = nameFilter;
    }

    /**
     * search screen filter getter
     * @return
     */
    public static String getDescriptionFilter() {
        return descriptionFilter;
    }

    /**
     * search screen filter setter
     * @param descriptionFilter
     */
    public static void setDescriptionFilter(String descriptionFilter) {
        PCCTRL.descriptionFilter = descriptionFilter;
    }

    /**
     * search screen filter getter
     * @return
     */
    public static int getInstallationDateModeFilter() {
        return installationDateModeFilter;
    }

    /**
     * search screen filter setter
     * @param installationDateModeFilter
     */
    public static void setInstallationDateModeFilter(int installationDateModeFilter) {
        PCCTRL.installationDateModeFilter = installationDateModeFilter;
    }

    /**
     * search screen filter getter
     * @return
     */
    public static Date getInstalationDateFilter() {
        return instalationDateFilter;
    }

    /**
     * search screen filter setter
     * @param instalationDateFilter
     */
    public static void setInstalationDateFilter(Date instalationDateFilter) {
        PCCTRL.instalationDateFilter = instalationDateFilter;
    }

    /**
     * search screen filter getter
     * @return
     */
    public static int getWarrentyModeFilter() {
        return warrentyModeFilter;
    }

    /**
     * search screen filter setter
     * @param warrentyModeFilter
     */
    public static void setWarrentyModeFilter(int warrentyModeFilter) {
        PCCTRL.warrentyModeFilter = warrentyModeFilter;
    }

    /**
     * search screen filter getter
     * @return
     */
    public static int getStatusFilter() {
        return statusFilter;
    }

    /**
     * search screen filter setter
     * @param statusFilter
     */
    public static void setStatusFilter(int statusFilter) {
        PCCTRL.statusFilter = statusFilter;
    }

    /**
     * search screen filter getter
     * @return
     */
    public static boolean[] getSelectedComponentsFilter() {
        return PCCTRL.selectedComponentsFilter;
    }
    
    /**
     * search screen filter setter
     * @param selectedComponentsFilter
     */
    public static void setSelectedComponentsFilter (boolean[] selectedComponentsFilter) {
        PCCTRL.selectedComponentsFilter = selectedComponentsFilter;
    }
    
    /**
     * search screen filter getter
     * @return
     */
    public static boolean[] getSelectedSpecsFilter() {
        return PCCTRL.selectedSpecsFilter;
    }
    
    /**
     * search screen filter setter
     * @param selectedSpecsFilter
     */
    public static void setSelectedSpecsFilter (boolean[] selectedSpecsFilter) {
        PCCTRL.selectedSpecsFilter = selectedSpecsFilter;
    }
    
    /**
     * set enabled component for search screen content
     * @param enaComp
     */
    public static void setEnaComp(ArrayList<Component> enaComp) {
        PCCTRL.enaComp = enaComp;
    }
    
    private static ArrayList<Component> getEnaComp() {
        return enaComp;
    }

    /**
     * get enabled component for search screen content
     * @return
     */
    public static ArrayList<PCSpec> getEnaSpec() {
        return enaSpec;
    }
    
    public static ArrayList<String> getEnaSpecStringArr() {
        ArrayList<String> enaSpecStringArr = new ArrayList<String>();
        if (enaSpec != null)
            for (PCSpec spc : enaSpec)
                enaSpecStringArr.add(spc.toString());
        return enaSpecStringArr;
    }

    /**
     * set enabled PC-specifications for search screen content
     * @param enaSpec
     */
    public static void setEnaSpec(ArrayList<PCSpec> enaSpec) {
        PCCTRL.enaSpec = enaSpec;
    }
    
    /**
     * This method implements close button pressed event in the PC search screen
     */
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new NetMapSCR());
    }

    /**
     * This method implements Search button pressed event in the PC search screen
     * @throws IOException
     */
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

    /**
     * This method implements Open Component management screen button pressed event in the PC search screen
     */
    public static void openPCCompSCRBtnPressed() {
        PCNMClientStart.switchPanels(new PCCompSCR());
    }

    /**
     * This method sets content for the PC search screen and then open it
     */
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

    /**
     * This method implements Open PS Specification management screen button press event in the PC search screen
     */
    public static void openPCSpecSCRBtnPressed() {
        
    }

    /**
     * This method process PC search results and open the PC search results screen
     * @param search_result
     */
    public static void processPCSearchReasult(ArrayList<PC> search_result) {
        PCNMClientStart.cur_ent.setPcs(search_result);
        pc_pull = PCNMClientStart.cur_ent.getPcs();
        ArrayList<String>pc_tbl = new ArrayList<String>();
        for (PC pc : pc_pull)
            pc_tbl.add(pc.toString());
        PCNMClientStart.appWindow.setTitle("PCNM - PC Search Results");
        PCNMClientStart.switchPanels(new PCSearchResultSCR(pc_tbl));
    }

    public static void searchResaultCloseBtnPressed() throws IOException {
        PCNMClientStart.appWindow.setTitle("PCNM - Manage PCs");
        Component cmp = new Component(null, null, 0, 0, Status.ENABLE);
        PCSpec spc = new PCSpec(null, null, 0, 0, 0, Status.ENABLE);
        PCNMClientStart.gotAllData = 0;
        PCNMClientModel.sendMessageToServer(new Message(MessageType.GET_COMP_ENABLE, cmp));
        PCCTRL.setEnaSpec(new ArrayList<PCSpec>());
        PCNMClientModel.sendMessageToServer(new Message(MessageType.GET_SPEC_ENABLE, spc));
    }

    /**
     * This method return true if Component's id+name combination is unique
     * @param ID
     * @param name
     * @return
     * @throws IOException
     */
    public static boolean isNameUnique(int ID, String name) throws IOException {
        if (pcQD == null) throw new IOException("Can't verify new component");
        for (QuickDic qd : pcQD)
            if (qd.getID() != ID && qd.getFirstVal().equalsIgnoreCase(name))
                return false;
        return true;
    }

    public static void AddPCBtnPressed(String name, String description, Date instDate, String[] spec, String status) throws IOException {
        Status sts = stringToStatus(status);
        Status specSts = stringToStatus(spec[6]);
        
        PCNMClientModel.sendMessageToServer(new Message(MessageType.ADD_PC,
                                            new PC( name,
                                                    description,
                                                    new PCSpec( Integer.parseInt(spec[0]),
                                                                spec[1],
                                                                spec[2],
                                                                Integer.parseInt(spec[3]),
                                                                Float.parseFloat(spec[4]),
                                                                Integer.parseInt(spec[5]),
                                                                specSts),
                                                    instDate,
                                                    sts,
                                                    null)));
    }

    /**
     * This method sets Quick-Dictionary of PCs in order to lower DB transaction costs
     * @param dic
     */
    public static void setPCDic(ArrayList<QuickDic> dic) {
        pcQD = dic;
    }

    /**
     * This Method implements processing add or update PC command's results
     * @param msgType
     * @param pc
     */
    public static void refreshPCWindow(MessageType msgType, PC pc) {
        ArrayList<String>pc_tbl = new ArrayList<String>();
        if (pc != null && msgType == MessageType.ADD_PC) {
            PCNMClientStart.cur_ent.addToPcs(pc);
            pcQD.add(new QuickDic(pc.getID(), pc.getName()));
        } else if (pc != null && msgType == MessageType.UPDATE_COMPONENT) {
            PCNMClientStart.cur_ent.updatePcs(pc);
            for (QuickDic qd : pcQD)
                if (qd.getID() == pc.getID()) {
                    String[] vals = {pc.getName()};
                    qd.setVals(vals);
                }
        }
        pc_pull = PCNMClientStart.cur_ent.getPcs();
        for (PC row : pc_pull)
            pc_tbl.add(row.toString());
        PCNMClientStart.switchPanels(new PCSearchResultSCR(pc_tbl));
    }
}

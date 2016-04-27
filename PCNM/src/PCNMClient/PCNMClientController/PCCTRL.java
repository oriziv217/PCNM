package PCNMClient.PCNMClientController;

import Entities.Component;
import Entities.Message;
import Entities.MessageType;
import Entities.Status;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.NetMapSCR;
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
    private static ArrayList<Component> enaComp;

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

    public static void searchByCompBtnPressed() throws IOException {
        
    }

    public static void searchByPCFilterBtnPressed() throws IOException {
        
    }

    public static void searchByPCSpecBtnPressed() throws IOException {
        
    }
    
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new NetMapSCR());
    }

    public static void setEnaComp(ArrayList<Component> enaComp) {
        PCCTRL.enaComp = enaComp;
    }
    
    private static ArrayList<Component> getEnaComp() {
        return enaComp;
    }
}

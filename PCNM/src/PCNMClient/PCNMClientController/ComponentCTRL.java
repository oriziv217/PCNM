package PCNMClient.PCNMClientController;

import Entities.Component;
import Entities.Message;
import Entities.MessageType;
import Entities.QuickDic;
import Entities.Status;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.ComponentSearchResultSCR;
import PCNMClient.PCNMClientView.NetMapSCR;
import PCNMClient.PCNMClientView.PCCompSCR;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class implements PC-Components screens controllers
 * @author Ori Ziv
 */
public class ComponentCTRL extends CTRL {
    // Components search screen filter settings
    private static String nameFilter;
    private static String descriptionFilter;
    private static int priceCmb = 0;
    private static float priceFilter = 0;
    private static int valAddCmb = 0;
    private static float valAddFilter = 0;
    private static int statusCmb = 0;
    private static ArrayList<Component> comp_pull;
    private static ArrayList<QuickDic> compQD;

    /**
     * screen filter getter
     * @return
     */
    public static String getNameFilter() {
        return nameFilter;
    }

    /**
     *
     * @param nameFilter
     */
    public static void setNameFilter(String nameFilter) {
        ComponentCTRL.nameFilter = nameFilter;
    }

    /**
     * screen filter getter
     * @return
     */
    public static String getDescriptionFilter() {
        return descriptionFilter;
    }

    /**
     *
     * @param descriptionFilter
     */
    public static void setDescriptionFilter(String descriptionFilter) {
        ComponentCTRL.descriptionFilter = descriptionFilter;
    }

    /**
     * screen filter getter
     * @return
     */
    public static int getPriceCmb() {
        return priceCmb;
    }

    /**
     * 
     * @param priceCmb
     */
    public static void setPriceCmb(int priceCmb) {
        ComponentCTRL.priceCmb = priceCmb;
    }

    /**
     * screen filter getter
     * @return
     */
    public static float getPriceFilter() {
        return priceFilter;
    }

    /**
     * screen filter setter
     * @param priceFilter
     */
    public static void setPriceFilter(float priceFilter) {
        ComponentCTRL.priceFilter = priceFilter;
    }

    /**
     * screen filter getter
     * @return
     */
    public static int getValAddCmb() {
        return valAddCmb;
    }

    /**
     * screen filter setter
     * @param valAddCmb
     */
    public static void setValAddCmb(int valAddCmb) {
        ComponentCTRL.valAddCmb = valAddCmb;
    }

    /**
     * screen filter getter
     * @return
     */
    public static float getValAddFilter() {
        return valAddFilter;
    }

    /**
     * screen filter setter
     * @param valAddFilter
     */
    public static void setValAddFilter(float valAddFilter) {
        ComponentCTRL.valAddFilter = valAddFilter;
    }

    /**
     * screen filter getter
     * @return
     */
    public static int getStatusCmb() {
        return statusCmb;
    }

    /**
     * screen filter setter
     * @param statusCmb
     */
    public static void setStatusCmb(int statusCmb) {
        ComponentCTRL.statusCmb = statusCmb;
    }
    
    /**
     * This method sets all filter fields at once
     * @param name
     * @param description
     * @param priceIDX
     * @param price
     * @param valAddIDX
     * @param valAdd
     * @param status
     */
    public static void setAllFilters(String name, String description, int priceIDX, float price, int valAddIDX, float valAdd, int status) {
        setNameFilter(name);
        setDescriptionFilter(description);
        setPriceCmb(priceIDX);
        setPriceFilter(price);
        setValAddCmb(valAddIDX);
        setValAddFilter(valAdd);
        setStatusCmb(status);
    }
    
    /**
     * This method sets Quick-Dictionary of components in order to lower DB transaction costs
     * @param dic
     */
    public static void setCompDic(ArrayList<QuickDic> dic) {
        compQD = dic;
    }
    
    /**
     * This method implements close button pressed event in Components search screen
     */
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new NetMapSCR());
    }

    /**
     * This method implements search button press event on components search screen
     * @throws IOException
     */
    public static void searchBtnPressed() throws IOException {
        // create search model with no filters at all
        Component cmp = new Component(null, null, 0, 0, Status.Error);
        // set name and description filters
        if (nameFilter != null && nameFilter.equals("") == false)
            cmp.setName(nameFilter);
        if (descriptionFilter != null && descriptionFilter.equals("") == false)
            cmp.setDescription(descriptionFilter);
        // set price and value add filters (unsigned means greater then or equal to)
        if (priceCmb > 0) {
            if (priceCmb == 1) cmp.setPrice(priceFilter);
            if (priceCmb == 2) cmp.setPrice(priceFilter * -1);
        }
        if (valAddCmb > 0) {
            if (valAddCmb == 1) cmp.setValueAdd(valAddFilter);
            if (valAddCmb == 2) cmp.setValueAdd(valAddFilter * -1);
        }
        // set status filter
        if (statusCmb > 0) cmp.setStatus(intToStatus(statusCmb));
        
        // send to the server
        PCNMClientModel.sendMessageToServer(new Message(MessageType.GET_COMP_WITH_FILTER, cmp));
    }

    /**
     * This method process PC-Components search result list for the GUI screen
     * @param search_result
     */
    public static void processSearchResults(ArrayList<Component> search_result) {
        PCNMClientStart.cur_ent.setCompnents(search_result);
        comp_pull = search_result;
        ArrayList<String>cmp_tbl = new ArrayList<String>();
        for (Component cmp : search_result) {
            cmp_tbl.add(cmp.toString());
        }
        PCNMClientStart.switchPanels(new ComponentSearchResultSCR(cmp_tbl));
    }

    /**
     * This method implements close button pressed event in the search results screen
     */
    public static void searchResaultCloseBtnPressed() {
        PCNMClientStart.switchPanels(new PCCompSCR());
    }

    /**
     * This method return true if Component's id+name combination is unique
     * @param ID
     * @param name
     * @return
     * @throws IOException
     */
    public static boolean isNameUnique(int ID, String name) throws IOException {
        if (compQD == null) throw new IOException("Can't verify new component");
        for (QuickDic qd : compQD)
            if (qd.getID() != ID && qd.getFirstVal().equalsIgnoreCase(name))
                return false;
        return true;
    }

    /**
     * This method implements Add Component button pressed event in the Add Component form within the Components search result screen
     * @param name
     * @param description
     * @param price
     * @param valueAdd
     * @param status
     * @throws IOException
     */
    public static void AddComponentBtnPressed(String name, String description, float price, float valueAdd, String status) throws IOException {
        Status sts = stringToStatus(status);
        PCNMClientModel.sendMessageToServer(new Message(MessageType.ADD_COMPONENT, new Component(name, description, price, valueAdd, sts)));
    }

    /**
     * This method implements Update Component button pressed event in the Update Component form within the Components search result screen
     * @param ID
     * @param name
     * @param description
     * @param price
     * @param valueAdd
     * @param status
     * @throws IOException
     */
    public static void UpdateComponentBtnPressed(int ID, String name, String description, float price, float valueAdd, String status) throws IOException {
        Status sts = stringToStatus(status);
        PCNMClientModel.sendMessageToServer(new Message(MessageType.UPDATE_COMPONENT, new Component(ID, name, description, price, valueAdd, sts)));
    }

    /**
     * This Method implements processing add or update component command's results
     * @param msgType
     * @param comp
     */
    public static void refreshComponentWindow(MessageType msgType, Component comp) {
        ArrayList<String>cmp_tbl = new ArrayList<String>();
        if (comp != null && msgType == MessageType.ADD_COMPONENT) {
            PCNMClientStart.cur_ent.addToComponents(comp);
            compQD.add(new QuickDic(comp.getID(), comp.getName()));
        } else if (comp != null && msgType == MessageType.UPDATE_COMPONENT) {
            PCNMClientStart.cur_ent.updateComponents(comp);
            for (QuickDic qd : compQD)
                if (qd.getID() == comp.getID()) {
                    String[] vals = {comp.getName()};
                    qd.setVals(vals);
                }
        }
        comp_pull = PCNMClientStart.cur_ent.getComponents();
        for (Component row : comp_pull)
            cmp_tbl.add(row.toString());
        PCNMClientStart.switchPanels(new ComponentSearchResultSCR(cmp_tbl));
    }
}

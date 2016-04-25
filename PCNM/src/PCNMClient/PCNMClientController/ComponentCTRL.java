package PCNMClient.PCNMClientController;

import Entities.Component;
import Entities.Message;
import Entities.MessageType;
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

    /**
     *
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
     *
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
     *
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
     *
     * @return
     */
    public static float getPriceFilter() {
        return priceFilter;
    }

    /**
     *
     * @param priceFilter
     */
    public static void setPriceFilter(float priceFilter) {
        ComponentCTRL.priceFilter = priceFilter;
    }

    /**
     *
     * @return
     */
    public static int getValAddCmb() {
        return valAddCmb;
    }

    /**
     *
     * @param valAddCmb
     */
    public static void setValAddCmb(int valAddCmb) {
        ComponentCTRL.valAddCmb = valAddCmb;
    }

    /**
     *
     * @return
     */
    public static float getValAddFilter() {
        return valAddFilter;
    }

    /**
     *
     * @param valAddFilter
     */
    public static void setValAddFilter(float valAddFilter) {
        ComponentCTRL.valAddFilter = valAddFilter;
    }

    /**
     *
     * @return
     */
    public static int getStatusCmb() {
        return statusCmb;
    }

    /**
     *
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

    public static void processSearchResults(ArrayList<Component> search_result) {
        PCNMClientStart.cur_ent.setCompnents(search_result);
        ArrayList<String>ws_tbl = new ArrayList<String>();
        for (Component cmp : search_result) {
            ws_tbl.add(cmp.toString());
        }
        PCNMClientStart.switchPanels(new ComponentSearchResultSCR(ws_tbl));
    }

    public static void searchResaultCloseBtnPressed() {
        PCNMClientStart.switchPanels(new PCCompSCR());
    }
}

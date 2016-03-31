package PCNMClient.PCNMClientController;

import Entities.Message;
import Entities.MessageType;
import Entities.PCUserType;
import Entities.Status;
import PCNMClient.PCNMClientModel;
import PCNMClient.PCNMClientStart;
import PCNMClient.PCNMClientView.NetMapSCR;
import java.io.IOException;
import java.util.ArrayList;

/**
 * this class implements PC-User-Types screen controls
 * @author Ori Ziv
 */
public class UserTypesCTRL extends CTRL {
    
    private static String fltrCol;
    private static String fltrStr;
    private static boolean fltrEnabled;
    private static String fltrImportance;

    /**
     *
     * @return
     */
    public static String getFltrCol() {
        return fltrCol;
    }

    /**
     *
     * @param fltrCol
     */
    public static void setFltrCol(String fltrCol) {
        UserTypesCTRL.fltrCol = fltrCol;
    }

    /**
     *
     * @return
     */
    public static String getFltrStr() {
        return fltrStr;
    }

    /**
     *
     * @param fltrStr
     */
    public static void setFltrStr(String fltrStr) {
        UserTypesCTRL.fltrStr = fltrStr;
    }

    /**
     *
     * @return
     */
    public static boolean isFltrEnabled() {
        return fltrEnabled;
    }

    /**
     *
     * @param fltrEnabled
     */
    public static void setFltrEnabled(boolean fltrEnabled) {
        UserTypesCTRL.fltrEnabled = fltrEnabled;
    }

    /**
     *
     * @return
     */
    public static String getFltrImportance() {
        return fltrImportance;
    }

    /**
     *
     * @param fltrImportance
     */
    public static void setFltrImportance(String fltrImportance) {
        UserTypesCTRL.fltrImportance = fltrImportance;
    }
    
    /**
     * This method resets all filter fields to their default values
     */
    public static void resetFilters () {
        fltrCol = new String("Show All");
        fltrStr = new String();
        fltrEnabled = false;
        fltrImportance = new String("Show All");
    }
    
    /**
     * This method formats server response for get users command for displaying
     * @param query_results
     * @return
     */
    public static ArrayList<String> processGetUserTypesResponse(ArrayList<PCUserType> query_results) {
        resetFilters();
        ArrayList<String> search_results = new ArrayList<String>();
        for (PCUserType typ : query_results)
            search_results.add(typ.toString());
        return search_results;
    }
    
    /**
     * This method implements close button pressed event in the PC User's Types screen
     */
    public static void closeBtnPressed () {
        PCNMClientStart.switchPanels(new NetMapSCR());
    }

    /**
     * This method implements Add new user OK button pressed event in the PC User's Types screen
     * @param name
     * @param description
     * @param importance
     * @param status
     * @throws IOException
     */
    public static void btnAddNewUserOKPressed(String name, String description, double importance, String status) throws IOException {
        Status sts = null;
        PCUserType pcuser;
                
        switch (status) {
            case "Enabled":
                sts = Status.ENABLE;
                break;
            case "Disabled":
                sts = Status.DISABLE;
                break;
            case "Suspended":
                sts = Status.SUSPENDED;
        }
        
        pcuser = new PCUserType(name, description, importance, sts);
        PCNMClientModel.sendMessageToServer(new Message(MessageType.ADD_PC_USER_TYPE, pcuser));
    }

    /**
     * * This method implements update user OK button pressed event in the PC User's Types screen
     * @param ID
     * @param name
     * @param description
     * @param importance
     * @param status
     * @throws IOException
     */
    public static void btnUpdateUserPressed(int ID, String name, String description, double importance, String status) throws IOException {
        Status sts = null;
        PCUserType pcuser;
                
        switch (status) {
            case "Enabled":
                sts = Status.ENABLE;
                break;
            case "Disabled":
                sts = Status.DISABLE;
                break;
            case "Suspended":
                sts = Status.SUSPENDED;
        }
        
        pcuser = new PCUserType(ID, name, description, importance, sts);
        PCNMClientModel.sendMessageToServer(new Message(MessageType.UPDATE_PC_USER_TYPE, pcuser));
    }
}

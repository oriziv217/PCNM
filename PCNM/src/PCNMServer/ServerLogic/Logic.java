package PCNMServer.ServerLogic;

import Entities.Status;

/**
 * This class implements some common business logic functionality
 * @author ori ziv
 */
public class Logic {

    /**
     * convert integer to Status
     * @param dbStatus
     * @return
     */
    protected static Status intToStatus(int dbStatus) {
        switch (dbStatus) {
            case 1:
                return Status.ENABLE;
            case 2:
                return Status.DISABLE;
            case 3:
                return Status.SUSPENDED;
        }
        return Status.Error;
    }
    
    /**
     * Convert Status to integer
     * @param status
     * @return
     */
    protected static int statusToInt(Status status) {
        switch (status) {
            case ENABLE:
                return 1;
            case DISABLE:
                return 2;
            case SUSPENDED:
                return 3;
        }
        return 4;
    }
    
    protected static Status stringToStatus (String status) {
        switch (status) {
            case "Enable":
            case "Enabled":
            case "enable":
            case "enabled":
            case "ENABLE":
            case "ENABLED":
                return Status.ENABLE;
            case "Disable":
            case "Disabled":
            case "disable":
            case "disabled":
            case "DISABLE":
            case "DISABLED":
                return Status.DISABLE;
            case "Suspend":
            case "Suspended":
            case "suspend":
            case "suspended":
            case "SUSPEND":
            case "SUSPENDED":
                return Status.SUSPENDED;
        }
        return Status.Error;
    }
    
    protected static String statusToString (Status status) {
        switch (status) {
            case ENABLE:
                return "Enabled";
            case DISABLE:
                return "Disabled";
            case SUSPENDED:
                return "Suspended";
        }
        return "Error";
    }
}

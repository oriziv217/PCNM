package PCNMServer.ServerLogic;

import Entities.Status;
import java.math.BigDecimal;

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
    
    /**
     * This method converts string to Status
     * @param status
     * @return
     */
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
    
    /**
     * This method converts Status to String
     * @param status
     * @return
     */
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
    
    /**
     * This method round a float to given precision
     * rule: 0.5 rounds up to 1
     * @param d
     * @param decimalPlace
     * @return
     */
    public static float roundFloat(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    
    /**
     * This method round a float to given precision
     * rule: 0.5 rounds up to 1
     * @param d
     * @param decimalPlace
     * @return
     */
    public static double roundDouble(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}

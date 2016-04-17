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
}

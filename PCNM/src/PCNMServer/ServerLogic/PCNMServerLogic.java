package PCNMServer.ServerLogic;

import PCNMServer.PCNMServer;
import PCNMServer.ServerResources.DBConnect;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class implements basic server functionality
 * @author Ori Ziv
 */
public class PCNMServerLogic {

    /**
     * This is the instance of the server.
     */
    public static PCNMServer pcnm = null;
    
    /**
     * This method set's the server port
     * @param port
     * @throws NumberFormatException
     */
    public static void initServer (String port) throws NumberFormatException {
        int serverPort;
        serverPort = Integer.parseInt(port);
        pcnm = new PCNMServer(serverPort);
    }
    
    /**
     * This method revert server's listener
     * @return true if server started otherwise return false
     * @throws IOException
     */
    public static boolean startStopListening () throws IOException {
        boolean listens;
        if (pcnm == null) return false;
        listens = pcnm.isListening();
        if (!listens) {
            pcnm.listen();
            listens = true;
        } else {
            pcnm.close();
            pcnm = null;
            listens = false;
        }
        return listens;
    }
    
    /**
     * This method sets My-SQL DB server connection string parameters
     * @param DBSrv
     * @param DBName
     * @param DBUser
     * @param DBpswd
     */
    public static void setDBParams (String DBSrv, String DBName, String DBUser, char[] DBpswd) {
        DBConnect.setMySQLConnection(DBSrv, DBName, DBUser, Arrays.toString(DBpswd));
    }
}

package PCNMClient;

import java.io.IOException;

/**
 * This class control the system's client
 * @author ori ziv
 */
public class PCNMClientModel {

    /**
     * This is the system's client instance
     */
    protected static PCNMClient client;
    
    /**
     * This method initiates the client and sets connection to the server
     * @param server
     * @param port
     * @return
     * @throws IOException
     */
    public static boolean initPCNMClient (String server, int port) throws IOException {
        if (port < 1) return false;
        client = new PCNMClient (server, port);
        return true;
    }
    
    /**
     *
     * @param msg
     * @throws IOException
     */
    public static void sendMessageToServer (Object msg) throws IOException {
        client.sendToServer(msg);
    }
    
    /**
     * This method ends the connection with the server
     * @throws java.io.IOException
     */
    public static void killConnection () throws IOException {
        if (client != null && client.isConnected())
            client.closeConnection();
    }
}

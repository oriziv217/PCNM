package PCNMClient;

import java.io.IOException;
import ocsf.client.AbstractClient;

/**
 * This class implements the system's client
 * @author ori ziv
 */
public class PCNMClient extends AbstractClient{

    /**
     * Constructor that starts client-server session
     * @param host - Server's host name or IP address
     * @param port - Server's listening port number
     * @throws IOException
     */
    public PCNMClient(String host, int port) throws IOException {
        super(host, port);
        openConnection();
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * This method closes client-server session
     * @throws java.io.IOException
     */
    public void quit() throws IOException {
        closeConnection();
    }
    
}

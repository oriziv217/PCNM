package PCNMServer;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class implements system's server
 * @author Ori Ziv
 */
public class PCNMServer extends AbstractServer {

    /**
     * default port used for client-server communication
     */
    public static final int DEFAULT_PORT = 11111;
    
    /**
     * Default constructor
     */
    public PCNMServer () {
        super (DEFAULT_PORT);
    }
    
    /**
     * Constructor
     * @param port client-server communication port
     */
    public PCNMServer (int port) {
        super (port);
    }
    
    // methods
    @Override
    /**
	   * This method handles any messages received from client.
	   *
	   * @param msg The message received from the client.
	   * @param client The connection from which the message originated.
	   */
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

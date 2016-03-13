package Entities;

/**
 * Enumeration of message types
 * @author Ori Ziv
 */
public enum MessageType {

    /**
     * Log in request message
     */
    LOGIN,

    /**
     * Log in answer message
     */
    LOGIN_ANSWER,
    
    /**
     * SQL Exception was thrown
     */
    DB_PROBLEM
}

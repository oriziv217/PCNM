package Entities;

import java.io.Serializable;

/**
 * Enumeration of Employee types
 * @author ori ziv
 */
public enum EmpType implements Serializable{

    /**
     * Technician = 1
     */
    TECHNICIAN,

    /**
     * MCSE = 2
     */
    MCSE,

    /**
     * CEO = 3
     */
    CEO,

    /**
     * Administrator = 4
     */
    ADMINISTRATOR,

    /**
     * Error type
     */
    Error;
    
}

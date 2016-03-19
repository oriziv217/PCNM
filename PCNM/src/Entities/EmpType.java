package Entities;

import java.io.Serializable;

/**
 * Enumeration of Employee types
 * @author ori ziv
 */
public enum EmpType implements Serializable{

    /**
     * CEO = 1
     */
    CEO,

    /**
     * MCSE = 2
     */
    MCSE,

    /**
     * Technician = 3
     */
    TECHNICIAN,

    /**
     * Administrator = 4
     */
    ADMINISTRATOR,

    /**
     * Error type
     */
    Error;    
}

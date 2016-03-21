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
     * Open Employees management screen
     */
    GET_EMPLOYEES,
    
    /**
     * Add new Employee
     */
    ADD_EMPLOYEE,
    
    /**
     * Employee added successfully to the DB
     */
    EMPLOYEE_ADDED,
    
    /**
     * Update Employee records
     */
    UPDATE_EMPLOYEES,
    /**
     * SQL Exception was thrown
     */
    DB_PROBLEM
}

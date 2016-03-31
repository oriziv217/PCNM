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
     * Open Manage User Types screen
     */
    GET_ALL_USERS,

    /**
     * Add new PC User Type
     */
    ADD_PC_USER_TYPE,
    
    /**
     * Update PC User Type
     */
    UPDATE_PC_USER_TYPE,
    
    /**
     * SQL Exception was thrown
     */
    DB_PROBLEM
}
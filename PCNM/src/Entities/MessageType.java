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
     * Open Manage Workstations screen
     */
    OPEN_WORKSTATION_SCREEN,
    
    /**
     * Get all Workstation types from DB
     */
    GET_ALL_WORKSTATION_TYPES,
    
    /**
     * Get Workstations from DB by filter
     */
    GET_WORKSTATIOS_WITH_FILTER,
    
    /**
     * Get all Workstation's ID and name for quick-dic list
     */
    GET_WORKSTATION_QUICKDIC,
    
    /**
     * Add new Workstation
     */
    ADD_WORKSTATION,
    
    /**
     * Update an existing Workstation
     */
    UPDATE_WORKSTATION,
    
    /**
     * Get all Workstation types from DB for management screen
     */
    MANAGE_WSTYPES,
    
    /**
     * Add new Workstation Type
     */
    ADD_WSTYPE,
    
    /**
     * Update an existing Workstation Type
     */
    UPDATE_WSTYPE,
    
    /**
     * Get Components from DB by filter
     */
    GET_COMP_WITH_FILTER,
    
    /**
     * Get all Component's ID and name for quick-dic list
     */
    GET_COMP_QUICKDIC,
    
    /**
     * Add new component
     */
    ADD_COMPONENT,
    
    /**
     * Update an existing Component
     */
    UPDATE_COMPONENT,
    
    /**
     * Get only enabled components
     */
    GET_COMP_ENABLE,
    
    /**
     * Get only enabled PC Specifications
     */
    GET_SPEC_ENABLE,
    
    /**
     * Search the DB for PCs according to search filters
     */
    PC_SEARCH,
    
    /**
     * SQL Exception was thrown
     */
    DB_PROBLEM
}

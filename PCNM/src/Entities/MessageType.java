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
     * Get All PC Specifications
     */
    Get_ALL_PCSPECS,
    
    /**
     * Get only enabled PC Specifications
     */
    GET_SPEC_ENABLE,
    
    /**
     * Add new PC Specification to the DB
     */
    ADD_PCSPEC,
    
    /**
     * Update PC Specification in the DB
     */
    UPDATE_PCSPEC,
    
    /**
     * Search the DB for PCs according to search filters
     */
    PC_SEARCH,
    
    /**
     * Get all PC ID and name for quick-dictionary list
     */
    GET_PC_QUICKDIC,
    
    /**
     * Add new PC
     */
    ADD_PC,
    
    /**
     * Update PC record in the DB
     */
    UPDATE_PC,
    
    /**
     * Get all Installed Components For a Specific PC
     */
    GET_PC_INST_COMP,
    
    /**
     * change PCComp records for a specific PC
     */
    CHANGE_PCCOMP,
    
    /**
     * Get all active couplings of PC-User Type-Workstations
     */
    GET_ACTIVE_TRIOS,
    
    /**
     * Get trio coupling full properties
     */
    VIEW_TRIO_PROP,
    
    /**
     * Get all available PCs
     */
    GET_PC_ADD_TRIO,
    
    /**
     * Get all available Workstations
     */
    GET_WORKSTATION_ADD_TRIO,
    
    /**
     * Get all available PC User Types
     */
    GET_PCUSERTYPE_ADD_TRIO,
    
    /**
     * Add new Trio coupling record to the DB
     */
    ADD_TRIO,
    
    /**
     * Set end date for a trio coupling
     */
    END_TRIO_PROP,
    
    /**
     * SQL Exception was thrown
     */
    DB_PROBLEM
}

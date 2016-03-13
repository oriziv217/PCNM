package Entities;

import java.io.Serializable;

/**
 * This class represents a record in Employee table
 * @author ori ziv
 */
public class Employee implements Serializable{
    private int ID;
    private String name;
    private String userName;
    private char [] password;
    private EmpType type;
    private Status status;

    // Constructors

    /**
     *
     * @param userName
     * @param password
     */

    public Employee(String userName, char[] password) {
        this.userName = userName;
        this.password = password;
    }
    
    /**
     *
     * @param name
     * @param userName
     * @param password
     * @param type
     * @param status
     */
    public Employee(String name, String userName, char[] password, EmpType type, Status status) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.status = status;
    }

    /**
     *
     * @param ID
     * @param name
     * @param userName
     * @param password
     * @param type
     * @param status
     */
    public Employee(int ID, String name, String userName, char[] password, EmpType type, Status status) {
        this.ID = ID;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.status = status;
    }

    /**
     *
     * @param emp
     */
    public Employee (Employee emp) {
        this.ID = emp.getID();
        this.name = emp.getName();
        this.password = emp.getPassword();
        this.status = emp.getStatus();
        this.type = emp.getType();
        this.userName = emp.getUserName();
    }
    // Setters and Getters

    /**
     *
     * @param ID
     */
    
    public void setID(int ID) {
        this.ID = ID;
    }
    
    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @param password
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

    /**
     *
     * @param type
     */
    public void setType(EmpType type) {
        this.type = type;
    }

    /**
     *
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public char[] getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    public EmpType getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public Status getStatus() {
        return status;
    }
}

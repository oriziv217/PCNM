package Entities;

import java.io.Serializable;

/**
 * This class implements a message object
 * @author Ori Ziv
 */
public class Message implements Serializable{
    private final MessageType msgType;
    private Object entity;
    private String dataString;

    /**
     *
     * @param msgType
     */
    public Message(MessageType msgType) {
        this.msgType = msgType;
    }

    /**
     *
     * @param msgType
     * @param entity
     */
    public Message(MessageType msgType, Object entity) {
        this.msgType = msgType;
        this.entity = entity;
    }

    /**
     *
     * @param msgType
     * @param dataString
     */
    public Message(MessageType msgType, String dataString) {
        this.msgType = msgType;
        this.dataString = dataString;
    }

    /**
     *
     * @param msgType
     * @param entity
     * @param dataString
     */
    public Message(MessageType msgType, Object entity, String dataString) {
        this.msgType = msgType;
        this.entity = entity;
        this.dataString = dataString;
    }

    /**
     *
     * @return
     */
    public MessageType getMsgType() {
        return msgType;
    }

    /**
     *
     * @return
     */
    public Object getEntity() {
        return entity;
    }

    /**
     *
     * @return
     */
    public String getDataString() {
        return dataString;
    }

    /**
     *
     * @param entity
     */
    public void setEntity(Object entity) {
        this.entity = entity;
    }

    /**
     *
     * @param dataString
     */
    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
    
}

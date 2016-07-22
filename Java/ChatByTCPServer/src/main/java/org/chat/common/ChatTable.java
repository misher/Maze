package org.chat.common;

/**
 *
 * Created by A.V.Tsaplin on 14.07.2016.
 */

public class ChatTable implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idMessage;
    private Integer idSession;
    private Integer connectNumbers;
    private String message;
    private String author;
    private String localAddress;

    public ChatTable() {
    }

    public ChatTable(int idSession, int connectNumbers, String message, String author, String localAddress) {
        this.idSession = idSession;
        this.connectNumbers = connectNumbers;
        this.message = message;
        this.author = author;
        this.localAddress = localAddress;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    public Integer getConnectNumbers() {
        return connectNumbers;
    }

    public void setConnectNumbers(Integer connectNumbers) {
        this.connectNumbers = connectNumbers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

}




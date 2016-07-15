package org.chat.common;

/**
 * Created by A.V.Tsaplin on 14.07.2016.
 */
public class ChatTable implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idMessage;
    private Integer idSession;
    private Integer idMessageThisSession;
    private String message;

    public ChatTable() {
    }

    public ChatTable(int idSession, int idMessageThisSession, String message) {
        this.idSession = idSession;
        this.idMessageThisSession = idMessageThisSession;
        this.message = message;
    }

    public Integer getIdMessageThisSession() {
        return idMessageThisSession;
    }

    public void setIdMessageThisSession(Integer idMessageThisSession) {
        this.idMessageThisSession = idMessageThisSession;
    }

    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}




package org.chat.common;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

/**
 *
 * Created by A.V.Tsaplin on 14.07.2016.
 */


@Entity
@Table(name = "chatTable", catalog = "chatbase")
@Component
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

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_message", length = 11, nullable = false)
    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    @Column(name = "id_session", length = 11, nullable = false)
    public Integer getIdSession() {
        return idSession;
    }

    public void setIdSession(Integer idSession) {
        this.idSession = idSession;
    }

    @Column(name = "connect_numbers", length = 11, nullable = false)
    public Integer getConnectNumbers() {
        return connectNumbers;
    }

    public void setConnectNumbers(Integer connectNumbers) {
        this.connectNumbers = connectNumbers;
    }

    @Column(name = "message", length = 45, nullable = false)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(name = "author", length = 25, nullable = false)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "local_address", length = 40, nullable = false)
    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

}




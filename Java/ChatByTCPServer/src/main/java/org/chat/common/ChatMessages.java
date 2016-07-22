package org.chat.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * Created by A.V.Tsaplin on 19.07.2016.
 */
public class ChatMessages{

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("message")
    private String message;
    @JsonProperty("localAddress")
    private String localAddress;

    public ChatMessages() {

    }

    public ChatMessages(String username, String password, String message, String localAddress) {
        this.username = username;
        this.password = password;
        this.message = message;
        this.localAddress = localAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }
}

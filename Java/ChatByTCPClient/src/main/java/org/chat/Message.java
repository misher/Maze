package org.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by A.V.Tsaplin on 19.07.2016.
 */
public class Message extends User {

    @JsonProperty("message")
    private String message;
    @JsonProperty("localAddress")
    private String localAddress;

    public Message(String username, String password, String message, String localAddress) {
        super(username, password);
        this.message = message;
        this.localAddress = localAddress;
    }
}

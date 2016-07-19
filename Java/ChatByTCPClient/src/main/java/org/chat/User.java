package org.chat;

/**
 * Created by A.V.Tsaplin on 18.07.2016.
 */

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "[username:" + username + ", password: " + password + "]";
    }

}
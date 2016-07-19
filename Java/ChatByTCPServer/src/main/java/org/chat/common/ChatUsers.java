package org.chat.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by A.V.Tsaplin on 18.07.2016.
 */
public class ChatUsers implements java.io.Serializable  {

    @JsonIgnore
    private Integer idUser;
    @JsonProperty("username")
    private String user;
    @JsonProperty("password")
    private String password;

    public ChatUsers() {
    }

    public ChatUsers(int idUser, String user, String password) {
        this.idUser = idUser;
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

}

package org.chat.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * Created by A.V.Tsaplin on 18.07.2016.
 */

@Entity
@Table(name = "chatUsers", catalog = "chatbase")
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

    @Column(name = "user", length = 25, nullable = false)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column(name = "password", length = 25, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_user", length = 11, nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

}

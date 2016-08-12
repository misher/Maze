package org.multiTable;

import javax.persistence.*;
import javax.persistence.criteria.Order;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * Created by A.V.Tsaplin on 12.08.2016.
 */

@Entity
@Table(name = "t_users", catalog = "multitablebase")
public class Users {

    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;

    public Users() {

    }

    public Users(int id, String login, String password, String name, String surname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", length = 3, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "login", length = 25, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password", length = 25, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "name", length = 25, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname", length = 25, nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @OneToMany(mappedBy="user")
    public List<UsersRoles> getUsersRoles() { return usersRoles; }
    public void setUsersRoles(List<UsersRoles> usersRoles) { this.usersRoles = usersRoles; }
    private List<UsersRoles> usersRoles;

//    public UsersRoles getUsersRoles() { return usersRoles; }
//    public void setCustomer(UsersRoles usersRoles) { this.usersRoles = usersRoles; }
//    private UsersRoles usersRoles;

}

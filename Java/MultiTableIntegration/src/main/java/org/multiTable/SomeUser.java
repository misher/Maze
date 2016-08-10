package org.multiTable;

import java.util.List;


/**
 *
 * Created by A.V.Tsaplin on 10.08.2016.
 */


public class SomeUser {

    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String roleName;

    public SomeUser() {

    }

    public SomeUser(int id, String login, String password, String name, String surname, String roleName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private boolean checkForActs(List<String> someActs) {
        return false;
    }

}







//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    @Column(name = "id", length = 11, nullable = false)
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Column(name = "login", length = 25, nullable = false)
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    @Column(name = "password", length = 25, nullable = false)
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Column(name = "name", length = 25, nullable = false)
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Column(name = "surname", length = 25, nullable = false)
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "rolename", nullable = false)
//    public String getRoleName() {
//        return this.roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }
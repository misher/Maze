package org.chat.common;

/**
 * Created by A.V.Tsaplin on 25.07.2016.
 */
public class DataBaseInfo {

    private final String address;
    private final String user;
    private final String password;

    public DataBaseInfo(String address, String user, String password) {
        this.address = address;
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }
}

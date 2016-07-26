package org.chat;

import java.io.Console;

/**
 *
 * Created by A.V.Tsaplin on 18.07.2016.
 */

public class ReadUserData {

    private User user;
    private Console console;

    public ReadUserData(Console console) {
        this.console = console;
        this.user = new User("","");
    }

    public void readUserData() {
        String username = console.readLine("User Name: ");
        String password = console.readLine("User Password: ");
        this.user.setUsername(username);
        this.user.setPassword(password);
    }

    public User getUser() {
        return user;
    }

}

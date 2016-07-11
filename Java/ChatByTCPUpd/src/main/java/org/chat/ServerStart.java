package org.chat;

/**
 * Created by A.V.Tsaplin on 11.07.2016.
 */

public class ServerStart {

    public static void main (String[] args) {
        new ChatTCPServerManyThread();
    }
}

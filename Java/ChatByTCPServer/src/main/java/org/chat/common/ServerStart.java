package org.chat.common;

import org.apache.log4j.PropertyConfigurator;

import java.sql.SQLException;

/**
 *
 * Created by A.V.Tsaplin on 11.07.2016.
 */

public class ServerStart {

    public static void main (String[] args) throws SQLException, InterruptedException {

        // To configure logger (log4j) properties
        PropertyConfigurator.configure("log4j.properties");


        // Initialization for dataBases
        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
        int sessionId = new DataBaseInit(dataBaseInfo).dataBaseInit();

        // Start server
        new ServerConnection(sessionId, 0, new ConnectionHandler()).toAcceptConnection();

    }

}


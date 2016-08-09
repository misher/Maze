package org.multiTable;

import java.sql.SQLException;

/**
 * Created by A.V.Tsaplin on 10.08.2016.
 */
public class Start {

    public static void main(String args[]) {

        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/multiTableBase", "root", "mercedesg55amg");
        DataBaseInit dataBaseInit = new DataBaseInit(dataBaseInfo);
        try {
            dataBaseInit.dataBaseInit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.print("Data base was initialized successful.");
        }

    }
}

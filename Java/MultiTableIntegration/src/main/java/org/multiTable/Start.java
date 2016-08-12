package org.multiTable;

import java.io.IOException;
import java.sql.SQLException;


/**
 *
 * Created by A.V.Tsaplin on 10.08.2016.
 */

public class Start {

    public static void main(String args[]) throws IOException, SQLException {

        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/multiTableBase", "root", "mercedesg55amg");
        DataBaseInit dataBaseInit = new DataBaseInit(dataBaseInfo);
        try {
            dataBaseInit.dataBaseInit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Data base was initialized successful.");

        CheckUserForAction checkUserForAction = new CheckUserForAction(dataBaseInfo);
        System.out.print(checkUserForAction.checkUserForAction(FieldToSearchEnum.NAME, "artur", "updateRepository"));

    }
}

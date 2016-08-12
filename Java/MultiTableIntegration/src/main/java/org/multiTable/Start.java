package org.multiTable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;


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

//        CheckUserForAction checkUserForAction = new CheckUserForAction(dataBaseInfo);
//        System.out.print(checkUserForAction.checkUserForAction(FieldToSearchEnum.NAME, "artur", "updateRepository"));


        Session session = new HibernateUtil().getSessionFactory().openSession();
        List<UsersRoles> listUsers = session.createQuery(" select ur from UsersRoles ur inner join ur.user ").list();
        System.out.println(listUsers.get(0).getUser().getName() + "  " + listUsers.get(0).getRoleId());
    }
}

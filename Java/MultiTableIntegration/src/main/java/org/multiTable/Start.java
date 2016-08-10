package org.multiTable;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
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

        String ableActs = "";
        String name = "artur";
        DataBaseJoin dataBaseJoin = new DataBaseJoin(dataBaseInfo);
        SomeUser someUser = dataBaseJoin.dataBaseSelectUserByName(name);

        try {
            ableActs = dataBaseJoin.dataBaseSelectByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        ObjectMapper mapper = new ObjectMapper();
        ActsJSon actsJSon = mapper.readValue(ableActs, ActsJSon.class);

        SomeRole someRole = new SomeRole(someUser.getRoleName(), actsJSon.getSomeActs());
        List<String> actsForCheck = new ArrayList<>();
        actsForCheck.add(AllActs.getAct(6));
        actsForCheck.add(AllActs.getAct(10));
        actsForCheck.add(AllActs.getAct(11));
        System.out.print(someRole.check(actsForCheck));

    }
}



//    Session session = HibernateUtil.getSessionFactory().openSession();
//session.beginTransaction();
//
//        String select = "select user.surname, roles.actionsjson from SomeUser user join user.rolename roles";
//        Query query = session.createQuery(select);
//        List elist = query.list();
//        session.getTransaction().commit();
//        session.close();

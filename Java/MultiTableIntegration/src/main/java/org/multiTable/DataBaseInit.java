package org.multiTable;

import java.sql.*;

/**
 *
 * Created by A.V.Tsaplin on 10.08.2016.
 */

public class DataBaseInit {

    // JDBC URL, username and password of MySQL server
    private final DataBaseInfo dataBaseInfo;

    public DataBaseInit (DataBaseInfo dataBaseInfo) {
        this.dataBaseInfo = dataBaseInfo;
    }

    public void dataBaseInit() throws SQLException {

        final String queryToDropUsers = "drop table IF EXISTS users";
        final String queryToDropRoles = "drop table IF EXISTS roles";

        final String queryCreateUsersTable = "create table if not exists users (id int(11) not null  AUTO_INCREMENT," +
                "login varchar(25) not null, password varchar(25) not null, name varchar(25) not null, surname varchar(25) not null, " +
                "rolename varchar(25) not null, primary key(id), FOREIGN KEY (rolename) REFERENCES roles(role)) engine = INNODB default charset = latin1";

        final String queryCreateRolesTable = "create table if not exists roles (role varchar(11) not null," +
                " actionsjson varchar(512) not null, primary key(role)) engine = INNODB default charset = latin1";


        final  String insertQueryMisher = "insert into multiTableBase.users (login, password, name, surname, rolename)" +
                " values('misher', 'qwefghuio', 'mikhail', 'tsaplin', 'owner')";
        final  String insertQueryIlya = "insert into multiTableBase.users (login, password, name, surname, rolename)" +
                " values('saaresto', 'subarulegacy', 'ilya', 'isakin', 'topmanager')";
        final  String insertQueryArtem = "insert into multiTableBase.users (login, password, name, surname, rolename)" +
                " values('dirtrider163', '20031994', 'artem', 'zaharov', 'systemadmin')";
        final  String insertQueryArtur = "insert into multiTableBase.users (login, password, name, surname, rolename)" +
                " values('vulgarusart', 'mercedesg55amg', 'artur', 'tsaplin', 'programmer')";


        int [] ownerArray = {0,1,2,3,4,5,6,7,8,9,10,11};
        ActsJSon ownerActs = new ActsJSon(ownerArray);
        final  String insertQueryOwner = "insert into multiTableBase.roles (role, actionsjson)" +
                " values('owner', '" + (new JSonConverter(ownerActs)).converte() +"')";

        int [] topManagerArray = {4,5,6,7};
        ActsJSon topManagerActs = new ActsJSon(topManagerArray);
        final  String insertQueryTopManager = "insert into multiTableBase.roles (role, actionsjson)" +
                " values('topmanager', '" + (new JSonConverter(topManagerActs)).converte() + "')";

        int [] systemAdminArray = {6,8,9};
        ActsJSon systemAdminActs = new ActsJSon(systemAdminArray);
        final  String insertQuerySystemAdmin = "insert into multiTableBase.roles (role, actionsjson)" +
                " values('systemadmin', '" + (new JSonConverter(systemAdminActs)).converte() + "')";

        int [] programmerArray = {6,10,11};
        ActsJSon programmerActs = new ActsJSon(programmerArray);
        final  String insertQueryProgrammer = "insert into multiTableBase.roles (role, actionsjson)" +
                " values('programmer', '" + (new JSonConverter(programmerActs)).converte() + "')";


        try (Connection con = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword()); Statement stmt = con.createStatement()) {

            //drop!
            stmt.executeUpdate(queryToDropUsers);
            stmt.executeUpdate(queryToDropRoles);

            // executing roles
            stmt.executeUpdate(queryCreateRolesTable);
            // executing users
            stmt.executeUpdate(queryCreateUsersTable);

            // executing insert query
            stmt.executeUpdate(insertQueryOwner);
            stmt.executeUpdate(insertQueryTopManager);
            stmt.executeUpdate(insertQuerySystemAdmin);
            stmt.executeUpdate(insertQueryProgrammer);

            stmt.executeUpdate(insertQueryMisher);
            stmt.executeUpdate(insertQueryIlya);
            stmt.executeUpdate(insertQueryArtem);
            stmt.executeUpdate(insertQueryArtur);



        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        }

    }
}

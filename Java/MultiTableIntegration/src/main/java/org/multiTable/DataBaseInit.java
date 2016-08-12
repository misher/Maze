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

        final String queryDeleteUsersTable = "drop table if exists t_users";
        final String queryDeleteRolesTable = "drop table if exists t_roles";
        final String queryDeleteActionsTable = "drop table if exists t_actions";
        final String queryDeleteUsersRolesTable = "drop table if exists t_users_roles";
        final String queryDeleteRolesActionsTable = "drop table if exists t_roles_actions";



        final String queryCreateUsersTable = "create table if not exists t_users (id int(3) not null  AUTO_INCREMENT," +
                "login varchar(25), password varchar(25), name varchar(25), surname varchar(25), " +
                "primary key(id)) engine = INNODB default charset = latin1";
        final String queryCreateRolesTable = "create table if not exists t_roles (id int(3) not null  AUTO_INCREMENT," +
                " role varchar(25), primary key(id)) engine = INNODB default charset = latin1";
        final String queryCreateActionsTable = "create table if not exists t_actions (id int(3) not null  AUTO_INCREMENT," +
                " action varchar(25), primary key(id)) engine = INNODB default charset = latin1";
        final String queryCreateUsersRolesTable = "create table if not exists t_users_roles (id int(3) not null  AUTO_INCREMENT," +
                " userid int(3), roleid int(3), primary key(id)) engine = INNODB default charset = latin1";
        final String queryCreateRolesActionsTable = "create table if not exists t_roles_actions (id int(3) not null  AUTO_INCREMENT," +
                " roleid int(3), actionid int(3), primary key(id)) engine = INNODB default charset = latin1";


        final  String insertQueryUsersMisher = "insert into multiTableBase.t_users (login, password, name, surname)" +
                " values('misher', 'qwefghuio', 'mikhail', 'tsaplin')";
        final  String insertQueryUsersIlya = "insert into multiTableBase.t_users (login, password, name, surname)" +
                " values('saaresto', 'subarulegacy', 'ilya', 'isakin')";
        final  String insertQueryUsersArtem = "insert into multiTableBase.t_users (login, password, name, surname)" +
                " values('dirtrider163', '20031994', 'artem', 'zaharov')";
        final  String insertQueryUsersArtur = "insert into multiTableBase.t_users (login, password, name, surname)" +
                " values('vulgarusart', 'mercedesg55amg', 'artur', 'tsaplin')";


        final String insertQueryRolesOwner = "insert into multiTableBase.t_roles (role)" +
                " values('owner')";
        final String insertQueryRolesTopManager = "insert into multiTableBase.t_roles (role)" +
                " values('topmanager')";
        final String insertQueryRolesSystemAdmin = "insert into multiTableBase.t_roles (role)" +
                " values('systemadmin')";
        final String insertQueryRolesProgrammer = "insert into multiTableBase.t_roles (role)" +
                " values('programmer')";


        final String insertQueryUsersRoles00 = "insert into multiTableBase.t_users_roles (userid, roleid)" +
                " values('1', '1')";
        final String insertQueryUsersRoles01 = "insert into multiTableBase.t_users_roles (userid, roleid)" +
                " values('1', '4')";
        final String insertQueryUsersRoles02 = "insert into multiTableBase.t_users_roles (userid, roleid)" +
                " values('2', '4')";
        final String insertQueryUsersRoles03 = "insert into multiTableBase.t_users_roles (userid, roleid)" +
                " values('3', '3')";
        final String insertQueryUsersRoles04 = "insert into multiTableBase.t_users_roles (userid, roleid)" +
                " values('4', '1')";
        final String insertQueryUsersRoles05 = "insert into multiTableBase.t_users_roles (userid, roleid)" +
                " values('4', '4')";





        try (Connection con = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword()); Statement stmt = con.createStatement()) {

            stmt.executeUpdate(queryDeleteUsersTable);
            stmt.executeUpdate(queryDeleteRolesTable);
            stmt.executeUpdate(queryDeleteActionsTable);
            stmt.executeUpdate(queryDeleteUsersRolesTable);
            stmt.executeUpdate(queryDeleteRolesActionsTable);

            stmt.executeUpdate(queryCreateUsersTable);
            stmt.executeUpdate(queryCreateRolesTable);
            stmt.executeUpdate(queryCreateActionsTable);
            stmt.executeUpdate(queryCreateUsersRolesTable);
            stmt.executeUpdate(queryCreateRolesActionsTable);

            stmt.executeUpdate(insertQueryUsersMisher);
            stmt.executeUpdate(insertQueryUsersIlya);
            stmt.executeUpdate(insertQueryUsersArtem);
            stmt.executeUpdate(insertQueryUsersArtur);

            stmt.executeUpdate(insertQueryRolesOwner);
            stmt.executeUpdate(insertQueryRolesTopManager);
            stmt.executeUpdate(insertQueryRolesSystemAdmin);
            stmt.executeUpdate(insertQueryRolesProgrammer);

            stmt.executeUpdate(insertQueryUsersRoles00);
            stmt.executeUpdate(insertQueryUsersRoles01);
            stmt.executeUpdate(insertQueryUsersRoles02);
            stmt.executeUpdate(insertQueryUsersRoles03);
            stmt.executeUpdate(insertQueryUsersRoles04);
            stmt.executeUpdate(insertQueryUsersRoles05);


            // insert all acts to table, which enumerated at allActs class
            AllActs allActs = AllActs.getInstance();
            for (int i = 0; i < allActs.getActs().size(); i++) {
                final String queryInsertActions = "insert into multiTableBase.t_actions (action)" +
                        " values('" + allActs.getAct(i)+ "')";
                stmt.executeUpdate(queryInsertActions);
            }

            for (int i = 1; i <= 12; i++) {
                final String queryInsertActions = "insert into multiTableBase.t_roles_actions (roleid, actionid)" +
                        " values('1', '" + i + "')";
                stmt.executeUpdate(queryInsertActions);
            }

            int [] secondIdArray = {5,6,7,8};
            for (int i = 0; i <= 3; i++) {
                final String queryInsertActions = "insert into multiTableBase.t_roles_actions (roleid, actionid)" +
                        " values('2', '" + secondIdArray[i] + "')";
                stmt.executeUpdate(queryInsertActions);
            }

            int [] thirdIdArray = {7,9,10};
            for (int i = 0; i <= 2; i++) {
                final String queryInsertActions = "insert into multiTableBase.t_roles_actions (roleid, actionid)" +
                        " values('3', '" + thirdIdArray[i] + "')";
                stmt.executeUpdate(queryInsertActions);
            }

            int [] fourthIdArray = {7,11,12};
            for (int i = 0; i <= 2; i++) {
                final String queryInsertActions = "insert into multiTableBase.t_roles_actions (roleid, actionid)" +
                        " values('4', '" + fourthIdArray[i] + "')";
                stmt.executeUpdate(queryInsertActions);
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        }

    }
}

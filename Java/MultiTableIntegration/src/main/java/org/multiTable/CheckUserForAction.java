package org.multiTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by A.V.Tsaplin on 12.08.2016.
 */
public class CheckUserForAction {

    private DataBaseInfo dataBaseInfo;

    public CheckUserForAction(DataBaseInfo dataBaseInfo) {
        this.dataBaseInfo = dataBaseInfo;
    }

    public boolean checkUserForActionByName(String name, String actionToCheck) throws SQLException {
        List<String> actionsList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword()); Statement stmt = con.createStatement()) {
            String queryOne = "select * from t_users join t_users_roles on t_users_roles.userid = t_users.id " +
                    "join t_roles_actions on t_users_roles.roleid = t_roles_actions.roleid join t_actions on t_roles_actions.actionid = t_actions.id where name = '" + name + "'";
            ResultSet resultSet = stmt.executeQuery(queryOne);
            while (resultSet.next()) {
                String action = resultSet.getString(13);
                actionsList.add(action);
            }
        }
        if (actionsList.contains(actionToCheck)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUserForActionBySurname(String surname, String actionToCheck) throws SQLException {
        List<String> actionsList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword()); Statement stmt = con.createStatement()) {
            String queryOne = "select * from t_users join t_users_roles on t_users_roles.userid = t_users.id " +
                    "join t_roles_actions on t_users_roles.roleid = t_roles_actions.roleid join t_actions on t_roles_actions.actionid = t_actions.id where surname = '" + surname + "'";
            ResultSet resultSet = stmt.executeQuery(queryOne);
            while (resultSet.next()) {
                String action = resultSet.getString(13);
                actionsList.add(action);
            }
        }
        if (actionsList.contains(actionToCheck)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUserForActionByLogin(String login, String actionToCheck) throws SQLException {
        List<String> actionsList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword()); Statement stmt = con.createStatement()) {
            String queryOne = "select * from t_users join t_users_roles on t_users_roles.userid = t_users.id " +
                    "join t_roles_actions on t_users_roles.roleid = t_roles_actions.roleid join t_actions on t_roles_actions.actionid = t_actions.id where login = '" + login + "'";
            ResultSet resultSet = stmt.executeQuery(queryOne);
            while (resultSet.next()) {
                String action = resultSet.getString(13);
                actionsList.add(action);
            }
        }
        if (actionsList.contains(actionToCheck)) {
            return true;
        } else {
            return false;
        }
    }


}

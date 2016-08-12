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

    public boolean checkUserForActionByName(String name, String action) throws SQLException {
        List<Integer> rolesEnnumerate = new ArrayList<>();
        List<String> actionsEnnumerate = new ArrayList<>();
        int dataBaseId = 0;
        try (Connection con = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword()); Statement stmt = con.createStatement()) {
            String queryOne = "select t_users.name, t_users_roles.roleid from t_users join t_users_roles on t_users_roles.userid = t_users.id " +
                    "where t_users.name = '" + name + "'";
            ResultSet resultSet = stmt.executeQuery(queryOne);
            while (resultSet.next()) {
                dataBaseId = resultSet.getInt(2);
                rolesEnnumerate.add(dataBaseId);
            }
            for (Integer i : rolesEnnumerate) {
                String queryTwo = "select t_roles_actions.roleid, t_actions.action from t_roles_actions join t_actions on t_actions.id = t_roles_actions.actionid " +
                        "where t_roles_actions.roleid = '" + i + "'";
                ResultSet resultSetTwo = stmt.executeQuery(queryTwo);
                while (resultSetTwo.next()) {
                    String someAction = resultSetTwo.getString(2);
                    actionsEnnumerate.add(someAction);
                }
            }
        }
        if (actionsEnnumerate.contains(action)) {
            return true;
        } else {
            return false;
        }
    }
}

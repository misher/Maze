package org.multiTable;

import java.sql.*;


/**
 *
 * Created by A.V.Tsaplin on 12.08.2016.
 */

public class CheckUserForAction {

    private DataBaseInfo dataBaseInfo;

    public CheckUserForAction(DataBaseInfo dataBaseInfo) {
        this.dataBaseInfo = dataBaseInfo;
    }

    public boolean checkUserForAction(FieldToSearchEnum fieldToSearchEnum, String name, String actionToCheck) throws SQLException {
        String searchBy = fieldToSearchEnum.getSearchString(fieldToSearchEnum);
        try (Connection con = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword()); Statement stmt = con.createStatement()) {
            String queryOne = "select * from t_users join t_users_roles on t_users_roles.userid = t_users.id " +
                    "join t_roles_actions on t_users_roles.roleid = t_roles_actions.roleid join t_actions on t_roles_actions.actionid = t_actions.id" +
                    " where " + searchBy + " = '" + name + "' and action = '" + actionToCheck + "'";
            ResultSet resultSet = stmt.executeQuery(queryOne);
            while (resultSet.next()) {
                return true;
            }
            return false;
        }
    }
}

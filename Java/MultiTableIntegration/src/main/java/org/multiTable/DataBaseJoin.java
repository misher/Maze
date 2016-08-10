package org.multiTable;

import java.sql.*;

/**
 *
 * Created by A.V.Tsaplin on 10.08.2016.
 */

public class DataBaseJoin {

    // JDBC URL, username and password of MySQL server
    private final DataBaseInfo dataBaseInfo;

    public DataBaseJoin (DataBaseInfo dataBaseInfo) {
        this.dataBaseInfo = dataBaseInfo;
    }

    public SomeUser dataBaseSelectUserByName(String name) throws SQLException {

        String searchName = name;
        final String queryJoin = "SELECT * FROM users where name = '" + searchName + "'";
        SomeUser someUser = new SomeUser();

        try (Connection con = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword()); Statement stmt = con.createStatement()) {
            // executing table select query
            ResultSet resultSet = stmt.executeQuery(queryJoin);
            while (resultSet.next()) {
                someUser.setId(resultSet.getInt(1));
                someUser.setLogin(resultSet.getString(2));
                someUser.setPassword(resultSet.getString(3));
                someUser.setName(resultSet.getString(4));
                someUser.setSurname(resultSet.getString(5));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        } finally {
            return someUser;
        }
    }


    public String dataBaseSelectByName(String name) throws SQLException {

        String searchName = name;
        final String queryJoin = "SELECT users.name, roles.actionsjson FROM users INNER JOIN roles ON roles.role = users.rolename where name = '" + searchName + "'";
        String json = "";

        try (Connection con = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword()); Statement stmt = con.createStatement()) {
            // executing table select query
            ResultSet resultSet = stmt.executeQuery(queryJoin);
            while (resultSet.next()) {
                json = resultSet.getString(2);
                System.out.println("Json: " + json);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        } finally {
            return json;
        }
    }
}

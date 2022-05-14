package com.example.system_testing.database;

import com.example.system_testing.auxiliary.Const;
import com.example.system_testing.essences.User;

import java.sql.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    /**
     * Подключение к БД.
     */

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public ResultSet getUser(User user) {

        ResultSet resultSet = null;

        String selectDB = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_LOGIN + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectDB);
            prSt.setString(1, user.getUserLogin());
            prSt.setString(2, user.getUserPassword());

            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }


}

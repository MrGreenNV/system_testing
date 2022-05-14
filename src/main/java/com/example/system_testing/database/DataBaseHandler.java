package com.example.system_testing.database;

import com.example.system_testing.auxiliary.Const;
import com.example.system_testing.essences.Teacher;
import com.example.system_testing.essences.User;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<String> getDisciplinesList () {

        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<>();
        String selectBD = "SELECT " + Const.DISCIPLINES_NAME + " FROM " + Const.DISCIPLINES_TABLE;
        PreparedStatement prSt;

        try {
            prSt = getDbConnection().prepareStatement(selectBD);
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                assert resultSet != null;
                if (!resultSet.next()) {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                list.add(resultSet.getString(Const.DISCIPLINES_NAME));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public ArrayList<String> getGroupsList() {

        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<>();
        String selectBD = "SELECT " + Const.GROUPS_NUMBER + " FROM " + Const.GROUPS_TABLE;
        PreparedStatement prSt;

        try {
            prSt = getDbConnection().prepareStatement(selectBD);
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                assert resultSet != null;
                if (!resultSet.next()) {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                list.add(resultSet.getString(Const.GROUPS_NUMBER));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }


    public void signUpUser(User user) {
        String insertDB = "INSERT INTO " + Const.USERS_TABLE + "(" + Const.USERS_LOGIN + ", " + Const.USERS_PASSWORD +
                ", " + Const.USERS_ROLE + ")" + "VALUES(?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertDB);

            prSt.setString(1, user.getUserLogin());
            prSt.setString(2, user.getUserPassword());
            prSt.setString(3, user.getUserRole());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signUpTeacher(Teacher teacher, int userID) {
        String insertDB = "INSERT INTO " + Const.TEACHERS_TABLE + "(" + Const.TEACHERS_FIO + ", "
                + Const.TEACHERS_USER_ID + ")" + "VALUES(?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertDB);

            prSt.setString(1, teacher.getFio());
            prSt.setInt(2, userID);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connectTeacherAndDisciplines(Teacher teacher) {
        String insertBD = "INSERT INTO " + Const.DISCIPLINES_HAS_TEACHERS_TABLE + "(" + Const.DISCIPLINES_HAS_TEACHERS_TEACHERS_ID + ", "
                + Const.DISCIPLINES_HAS_TEACHERS_DISCIPLINES_ID + ")" + "VALUES(?,?)";

        try {


            for (String discipline: teacher.getDisciplinesList()
                 ) {
                PreparedStatement prSt = getDbConnection().prepareStatement(insertBD);
                prSt.setInt(1, getTeacherID(teacher));
                prSt.setInt(2, getDisciplineID(discipline));
                prSt.executeUpdate();
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getUserID(User user) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + Const.USERS_TABLE +
                " WHERE " + Const.USERS_LOGIN + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectBD);
            prSt.setString(1, user.getUserLogin());
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                id = resultSet.getInt(Const.USERS_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    public int getTeacherID(Teacher teacher) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + Const.TEACHERS_TABLE +
                " WHERE " + Const.TEACHERS_FIO + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectBD);
            prSt.setString(1, teacher.getFio());
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                id = resultSet.getInt(Const.TEACHERS_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    public int getDisciplineID(String discipline) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + Const.DISCIPLINES_TABLE +
                " WHERE " + Const.DISCIPLINES_NAME + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectBD);
            prSt.setString(1, discipline);
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                id = resultSet.getInt(Const.DISCIPLINES_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }
}

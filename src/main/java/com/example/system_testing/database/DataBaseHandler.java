package com.example.system_testing.database;

import com.example.system_testing.auxiliary.ConstTables;
import com.example.system_testing.essences.*;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseHandler extends Configs {
    Connection dbConnection;


    /**
     * Подключение к БД.
     * @return - возвращает драйвед для соединения.
     * @throws ClassNotFoundException - ошибочки.
     * @throws SQLException - ошибочки.
     */
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    /**
     * Получение строки из БД с логином и паролем пользователя.
     * @param user - пользователь.
     * @return - возвращает строку данных пользователя.
     */
    public ResultSet getUser(User user) {

        ResultSet resultSet = null;

        String selectDB = "SELECT * FROM " + ConstTables.USERS_TABLE + " WHERE " + ConstTables.USERS_LOGIN + "=? AND " + ConstTables.USERS_PASSWORD + "=?";

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

    /**
     * Получение списка дисциплин из БД.
     * @return - возвращает список.
     */
    public ArrayList<String> getDisciplinesList () {

        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<>();
        String selectBD = "SELECT " + ConstTables.DISCIPLINES_NAME + " FROM " + ConstTables.DISCIPLINES_TABLE;
        PreparedStatement prSt;

        try {
            prSt = getDbConnection().prepareStatement(selectBD);
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                //assert resultSet != null;
                if (!resultSet.next()) {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                list.add(resultSet.getString(ConstTables.DISCIPLINES_NAME));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    /**
     * Получение списка групп из БД.
     * @return - возвращает список.
     */
    public ArrayList<String> getGroupsList() {

        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<>();
        String selectBD = "SELECT " + ConstTables.GROUPS_NUMBER + " FROM " + ConstTables.GROUPS_TABLE;
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
                list.add(resultSet.getString(ConstTables.GROUPS_NUMBER));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    /**
     * Получение списка пользователей из БД.
     * @return - возвращает список.
     */
    public ArrayList<String> getUserList() {

        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<>();
        String selectBD = "SELECT " + ConstTables.USERS_LOGIN + " FROM " + ConstTables.USERS_TABLE;
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
                list.add(resultSet.getString(ConstTables.USERS_LOGIN));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    /**
     * Запись данных пользователя в БД.
     * @param user - пользователь.
     */
    public void signUpUser(User user) {
        String insertDB = "INSERT INTO " + ConstTables.USERS_TABLE + "(" + ConstTables.USERS_LOGIN + ", " + ConstTables.USERS_PASSWORD +
                ", " + ConstTables.USERS_ROLE + ")" + "VALUES(?,?,?)";

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

    /**
     * Запись данных преподавателя в БД.
     * @param teacher - преподаватель.
     * @param userID - ID пользователя.
     */
    public void signUpTeacher(Teacher teacher, int userID) {
        String insertDB = "INSERT INTO " + ConstTables.TEACHERS_TABLE + "(" + ConstTables.TEACHERS_FIO + ", "
                + ConstTables.TEACHERS_USER_ID + ")" + "VALUES(?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertDB);

            prSt.setString(1, teacher.getFio());
            prSt.setInt(2, userID);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запись данных студента в БД.
     * @param student - студент.
     * @param userID - ID пользователя.
     * @param groupID - ID группы.
     */
    public void signUpStudent(Student student, int userID, int groupID) {
        String insertDB = "INSERT INTO " + ConstTables.STUDENTS_TABLE + "(" + ConstTables.STUDENTS_FIO + ", " + ConstTables.STUDENTS_USER_ID + ", "
                + ConstTables.STUDENTS_GROUPS_ID + ")" + "VALUES(?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertDB);

            prSt.setString(1, student.getFio());
            prSt.setInt(2, userID);
            prSt.setInt(3, groupID);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Запись данных о дисциплинах, которые ведут преподаватели.
     * @param teacher - преподаватель.
     */
    public void connectTeacherAndDisciplines(Teacher teacher) {
        String insertBD = "INSERT INTO " + ConstTables.DISCIPLINES_HAS_TEACHERS_TABLE + "(" + ConstTables.DISCIPLINES_HAS_TEACHERS_TEACHERS_ID + ", "
                + ConstTables.DISCIPLINES_HAS_TEACHERS_DISCIPLINES_ID + ")" + "VALUES(?,?)";

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

    /**
     * Получение ID пользователя из БД.
     * @param user - пользователь.
     * @return - возвращает целое число.
     */
    public int getUserID(User user) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + ConstTables.USERS_TABLE +
                " WHERE " + ConstTables.USERS_LOGIN + " =?";
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
                id = resultSet.getInt(ConstTables.USERS_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    /**
     * Получение ID пользователя из БД.
     * @param login - логин пользователя.
     * @return - возвращает целое число.
     */
    public int getUserID(String login) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + ConstTables.USERS_TABLE +
                " WHERE " + ConstTables.USERS_LOGIN + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectBD);
            prSt.setString(1, login);
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
                id = resultSet.getInt(ConstTables.USERS_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    /**
     * Получение ID преподавателя из БД.
     * @param teacher - преподаватель.
     * @return - возвращает целое число.
     */
    public int getTeacherID(Teacher teacher) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + ConstTables.TEACHERS_TABLE +
                " WHERE " + ConstTables.TEACHERS_FIO + " =?";
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
                id = resultSet.getInt(ConstTables.TEACHERS_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    /**
     * Получение ID преподавателя из БД.
     * @param userID - ID пользователя.
     * @return - возвращает ID преподавателя.
     */
    public int getTeacherID(int userID) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + ConstTables.TEACHERS_TABLE +
                " WHERE " + ConstTables.TEACHERS_USER_ID + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectBD);
            prSt.setInt(1, userID);
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
                id = resultSet.getInt(ConstTables.TEACHERS_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    /**
     * Получение ID дисциплины из БД.
     * @param discipline - название дисциплины.
     * @return - возвращает целое число.
     */
    public int getDisciplineID(String discipline) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + ConstTables.DISCIPLINES_TABLE +
                " WHERE " + ConstTables.DISCIPLINES_NAME + " =?";
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
                id = resultSet.getInt(ConstTables.DISCIPLINES_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    /**
     * Получение ID группы из БД.
     * @param group - номер группы
     * @return - возвращает целое число.
     */
    public int getGroupID(String group) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + ConstTables.GROUPS_TABLE +
                " WHERE " + ConstTables.GROUPS_NUMBER + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectBD);
            prSt.setString(1, group);
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
                id = resultSet.getInt(ConstTables.GROUPS_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    /**
     * Получение ID теста из БД.
     * @param nameTest - имя теста.
     * @return - возвращает ID теста.
     */
    public int getTestID(String nameTest) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + ConstTables.TESTS_TABLE +
                " WHERE " + ConstTables.TESTS_NAME + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectBD);
            prSt.setString(1, nameTest);
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
                id = resultSet.getInt(ConstTables.TESTS_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    public int getQuestionID(String nameQuestion) {
        int id = -1;
        ResultSet resultSet = null;
        String selectBD = "SELECT * FROM " + ConstTables.QUESTIONS_TABLE +
                " WHERE " + ConstTables.QUESTIONS_NAME + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectBD);
            prSt.setString(1, nameQuestion);
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
                id = resultSet.getInt(ConstTables.QUESTIONS_ID);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return id;
    }

    /**
     * Удаление пользователя из системы.
     * @param user - удаляемый пользователь.
     */
    public void deleteUser(String user) {
        int userID = getUserID(user);

        String deleteBD = "DELETE FROM " + ConstTables.USERS_TABLE + " WHERE " +
                ConstTables.USERS_ID + " = " + userID;
        try {
            PreparedStatement prSt1 = getDbConnection().prepareStatement(deleteBD);
            prSt1.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавление дисциплины в БД.
     * @param discipline - наименование дисциплины.
     */
    public void addDiscipline(String discipline) {
        String insertDB = "INSERT INTO " + ConstTables.DISCIPLINES_TABLE + "(" + ConstTables.DISCIPLINES_NAME + ")" + "VALUES(?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertDB);

            prSt.setString(1, discipline);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаление дисциплины из БД.
     * @param discipline - наименование дисциплины.
     */
    public void deleteDiscipline(String discipline) {
        int disciplineID = getDisciplineID(discipline);

        String deleteBD = "DELETE FROM " + ConstTables.DISCIPLINES_TABLE + " WHERE " +
                ConstTables.DISCIPLINES_ID + " = " + disciplineID;
        try {
            PreparedStatement prSt1 = getDbConnection().prepareStatement(deleteBD);
            prSt1.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаление группы из БД.
     * @param group - номер группы.
     */
    public void deleteGroup(String group) {
        int groupID = getGroupID(group);

        String deleteBD = "DELETE FROM " + ConstTables.GROUPS_TABLE + " WHERE " +
                ConstTables.GROUPS_ID + " = " + groupID;
        try {
            PreparedStatement prSt1 = getDbConnection().prepareStatement(deleteBD);
            prSt1.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавление группы в БД.
     * @param group - номер группы.
     */
    public void addGroup(String group) {
        String insertDB = "INSERT INTO " + ConstTables.GROUPS_TABLE + "(" + ConstTables.GROUPS_NUMBER + ")" + "VALUES(?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertDB);

            prSt.setString(1, group);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создание нового теста.
     * @param test - тест.
     */
    public void createTest(Test test) {

        String insertDB = "INSERT INTO " + ConstTables.TESTS_TABLE + "(" + ConstTables.TESTS_NAME + ", " +
                ConstTables.TESTS_DISCIPLINES_ID + ")" + "VALUES(?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertDB);

            prSt.setString(1, test.getNameTest());
            prSt.setInt(2, getDisciplineID(test.getDiscipline()));

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Добавление вопроса и ответов на него в БД.
     * @param test  - тест.
     * @param question - вопрос.
     */
    public void addQuestionInBD(Test test, Question question) {
        String insertQuestionDB = "INSERT INTO " + ConstTables.QUESTIONS_TABLE + "(" + ConstTables.QUESTIONS_NAME + ", " +
                ConstTables.QUESTIONS_TESTES_ID + ")" + "VALUES(?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insertQuestionDB);

            prSt.setString(1, question.getNameQuestion());
            prSt.setInt(2, getTestID(test.getNameTest()));

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Answer answer: question.getAnswerList()
             ) {
            String insertAnswerBD =  "INSERT INTO " + ConstTables.ANSWERS_TABLE + "(" + ConstTables.ANSWERS_NAME + ", " +
                    ConstTables.ANSWERS_IS_TRUE + ", " + ConstTables.ANSWERS_QUESTION_ID + ")" + "VALUES(?, ?, ?)";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insertAnswerBD);

                prSt.setString(1, answer.getNameAnswer());
                prSt.setBoolean(2, answer.getIsTrueAnswer());
                prSt.setInt(3, getQuestionID(question.getNameQuestion()));

                prSt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получение списка дисциплин для конкретного преподавателя.
     * @param disciplinesList - входящий список (пустой)ю
     * @param userID - ID пользователя.
     * @return - возвращает список дисциплин.
     */
    public ArrayList<String> getDisciplinesListFromTeacher(ArrayList<String> disciplinesList, int userID) {

        int teacherID = getTeacherID(userID);
        ResultSet resultSetOne = null;
        ResultSet resultSetTwo = null;

        String selectBD = "SELECT " + ConstTables.DISCIPLINES_HAS_TEACHERS_DISCIPLINES_ID + " FROM " +
                ConstTables.DISCIPLINES_HAS_TEACHERS_TABLE  +
                " WHERE " + ConstTables.DISCIPLINES_HAS_TEACHERS_TEACHERS_ID + " =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectBD);
            prSt.setInt(1, teacherID);
            resultSetOne = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                //assert resultSetOne != null;
                if (!resultSetOne.next()) {
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {

                int disciplineID = resultSetOne.getInt(ConstTables.DISCIPLINES_HAS_TEACHERS_DISCIPLINES_ID);
                String selectDisciplineBD = "SELECT " + ConstTables.DISCIPLINES_NAME + " FROM " +
                        ConstTables.DISCIPLINES_TABLE  +
                        " WHERE " + ConstTables.DISCIPLINES_ID + " =?";

                try {
                    PreparedStatement prSt = getDbConnection().prepareStatement(selectDisciplineBD);
                    prSt.setInt(1, disciplineID);
                    resultSetTwo = prSt.executeQuery();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        //assert resultSetTwo != null;
                        if (!resultSetTwo.next()) {
                            break;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    disciplinesList.add(resultSetTwo.getString(ConstTables.DISCIPLINES_NAME));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return disciplinesList;

    }
}

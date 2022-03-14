/*
package tbspring.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tbspring.models.ActiveUserModel;
import tbspring.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDBUser {
    public static void getUserId(ActiveUserModel activeUser) throws SQLException {
        Connection connection = ServiceDB.getConnection();
        PreparedStatement getUser = connection.prepareStatement("SELECT idUser FROM users WHERE user_name = ?;");
        String userName = activeUser.getUserName();
        getUser.setString(1, userName);
        ResultSet rsGetUser = getUser.executeQuery();
        while (rsGetUser.next()) {
            activeUser.setUserId(rsGetUser.getInt(1));
        }
        connection.close();
    }

    public static String getUserPosition(int userId) throws SQLException {
        Connection connection = ServiceDB.getConnection();
        PreparedStatement getUserPosition = connection.prepareStatement("SELECT position FROM users WHERE idUser = ?;");
        getUserPosition.setInt(1, userId);
        ResultSet userPosition = getUserPosition.executeQuery();
        userPosition.next();
        connection.close();
        return userPosition.getString("position");
    }

    public static ObservableList<User> getAllUsers() throws SQLException {
        Connection connection = ServiceDB.getConnection();
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        PreparedStatement getAllUsersSt = connection.prepareStatement("SELECT user_name, idUser FROM users;");
        ResultSet getAllUsersRs = getAllUsersSt.executeQuery();
        while (getAllUsersRs.next()) {
            User temp = new User();
            temp.setUserId(getAllUsersRs.getInt("idUser"));
            temp.setUserName(getAllUsersRs.getString("user_name"));
            allUsers.add(temp);
        }
        connection.close();
        return allUsers;
    }

    public static ActiveUserModel setActiveUser(String userName) throws SQLException {
        Connection connection = ServiceDB.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE user_name = ?;");
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        ActiveUserModel activeUser = new ActiveUserModel();
        while (rs.next()) {
            activeUser.setUserId(rs.getInt("idUser"));
            activeUser.setUserName(rs.getString("user_name"));
            activeUser.setUserPic(rs.getString("userpic"));
            //TODO: переводить String в Enum
            activeUser.setGender(rs.getString("gender").equals("MALE") ?
                    User.GENDER.MALE : User.GENDER.FEMALE);
            activeUser.setPosition(rs.getString("position").equals("MANAGER") ?
                    User.POSITION.MANAGER : User.POSITION.EXECUTOR);
            activeUser.setPhone(rs.getBytes("phone"));
            activeUser.setEmail(rs.getString("e-mail"));
            //директория на сервере хранится с экранированнными обратными слэшами. Здесь слэш деэкранируется
            String directory = rs.getString("directory");
            if (directory != null) {
                activeUser.setDirectory(rs.getString("directory").replace("\\\\", "\\"));
            } else {
                activeUser.setDirectory("Рабочая папка не указана");
            }
        }
        connection.close();
        return activeUser;
    }

    public static void executeUpdate(String sqlQuery) throws SQLException {
        Connection connection = ServiceDB.getConnection();
        PreparedStatement executeUpdate = connection.prepareStatement(sqlQuery);
        executeUpdate.executeUpdate();
        connection.close();
    }

    public static String getUserName(int executor_id) throws SQLException {
        Connection connection = ServiceDB.getConnection();
        PreparedStatement getUser = connection.prepareStatement("SELECT user_name FROM users WHERE idUser = ?;");
        getUser.setInt(1, executor_id);
        ResultSet rsGetUserName = getUser.executeQuery();
        rsGetUserName.next();
        connection.close();
        return rsGetUserName.getString("user_name");
    }

    public static User getUser(String userName) throws SQLException {
        Connection connection = ServiceDB.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users where user_name = ?;");
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while (rs.next()) {
            user.setUserName(userName);
            user.setUserId(rs.getInt("idUser"));
            user.setUserPic(rs.getString("userpic"));
            user.setGender(rs.getString("gender").equals("MALE") ?
                    User.GENDER.MALE : User.GENDER.FEMALE);
            user.setPosition(rs.getString("position").equals("MANAGER") ?
                    User.POSITION.MANAGER : User.POSITION.EXECUTOR);
            user.setPhone(rs.getBytes("phone"));
            user.setEmail(rs.getString("e-mail"));
            String directory = rs.getString("directory");
            if (directory != null) {
                user.setDirectory(rs.getString("directory").replace("\\\\", "\\"));
            } else {
                user.setDirectory("Рабочая папка не указана");
            }
        }
        connection.close();
        return user;
    }

    public static User getUser(long idUser) throws SQLException {
        Connection connection = ServiceDB.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users where idUser = ?;");
        ps.setLong(1, idUser);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while (rs.next()) {
            user.setUserId(idUser);
            user.setUserName(rs.getString("user_name"));
            user.setUserPic(rs.getString("userpic"));
            user.setGender(rs.getString("gender").equals("MALE") ?
                    User.GENDER.MALE : User.GENDER.FEMALE);
            user.setPosition(rs.getString("position").equals("MANAGER") ?
                    User.POSITION.MANAGER : User.POSITION.EXECUTOR);
            user.setPhone(rs.getBytes("phone"));
            user.setEmail(rs.getString("e-mail"));
            String directory = rs.getString("directory");
            if (directory != null) {
                user.setDirectory(rs.getString("directory").replace("\\\\", "\\"));
            } else {
                user.setDirectory("Рабочая папка не указана");
            }
        }
        connection.close();
        return user;
    }
}

*/

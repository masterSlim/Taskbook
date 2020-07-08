package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Current_User;
import models.User;
import models.User_Lite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service_User_DB {
    public static void getUserId(String userName) throws SQLException {
        PreparedStatement getUser = Service_DB.getConnection().prepareStatement("SELECT user_id FROM users WHERE user_name = ?;");
        getUser.setString(1, userName);
        ResultSet rsGetUser = getUser.executeQuery();
        while (rsGetUser.next()) {
            Current_User.setUserId(rsGetUser.getInt(1));
        }
        Service_DB.getConnection().close();
    }

    public static String getUserPosition(int userId) throws SQLException {
        PreparedStatement getUserPosition = Service_DB.getConnection().prepareStatement("SELECT position FROM users WHERE user_id = ?;");
        getUserPosition.setInt(1, userId);
        ResultSet userPosition = getUserPosition.executeQuery();
        userPosition.next();
        Service_DB.getConnection().close();
        return userPosition.getString("position");
    }

    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        PreparedStatement getAllUsersSt = Service_DB.getConnection().prepareStatement("SELECT user_name, user_id FROM users;");
        ResultSet getAllUsersRs = getAllUsersSt.executeQuery();
        while (getAllUsersRs.next()) {
            User_Lite temp = new User_Lite();
            temp.setUserId(getAllUsersRs.getInt("user_id"));
            temp.setUserName(getAllUsersRs.getString("user_name"));
            allUsers.add(temp);
        }
        Service_DB.getConnection().close();
        return allUsers;
    }

    public static void setCurrentUser(String userName) throws SQLException {
        PreparedStatement ps = Service_DB.getConnection().prepareStatement("SELECT * FROM users WHERE user_name = ?;");
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Current_User.setUserId(rs.getInt("idUser"));
            Current_User.setUserName(rs.getString("user_name"));
            Current_User.setUserpick(rs.getString("userpic"));
            Current_User.setGender(rs.getBoolean("gender"));
            Current_User.setPosition(rs.getString("position"));
            Current_User.setPhone(rs.getString("phone"));
            Current_User.setEmail(rs.getString("e-mail"));
            //директория на сервере хранится с экранированнными обратными слэшами. Здесь слэш деэкранируется
            String directory = rs.getString("directory");
            if (directory != null) {
                Current_User.setDirectory(rs.getString("directory").replace("\\\\", "\\"));
            } else {
                Current_User.setDirectory("Рабочая папка не указана");
            }
        }
        Service_DB.getConnection().close();
    }

    public static void executeUpdate(String sqlQuery) throws SQLException {
        System.out.println(sqlQuery);
        PreparedStatement executeUpdate = Service_DB.getConnection().prepareStatement(sqlQuery);
        executeUpdate.executeUpdate();
        Service_DB.getConnection().close();

    }

    public static String getUserName(int executor_id) throws SQLException {
        PreparedStatement getUser = Service_DB.getConnection().prepareStatement("SELECT user_name FROM users WHERE user_id = ?;");
        getUser.setInt(1, executor_id);
        ResultSet rsGetUserName = getUser.executeQuery();
        rsGetUserName.next();
        Service_DB.getConnection().close();
        return rsGetUserName.getString("user_name");
    }

    public static User getUser(String userName) throws SQLException {
        //статический ли? под вопросом
        PreparedStatement ps = Service_DB.getConnection().prepareStatement("SELECT * FROM users where user_name = ?;");
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while (rs.next()) {
            user.setUserName(userName);
            user.setUserId(rs.getInt("user_id"));
            user.setUserpick(rs.getString("userpic"));
            user.setPosition(rs.getString("position"));
            user.setPhone(rs.getLong("phone"));
            user.setEmail(rs.getString("e-mail"));
            user.setGender(rs.getBoolean("gender"));
            String directory = rs.getString("directory");
            if (directory != null) {
                user.setDirectory(rs.getString("directory").replace("\\\\", "\\"));
            } else {
                user.setDirectory("Рабочая папка не указана");
            }
        }
        Service_DB.getConnection().close();
        return user;
    }

    public static User getUser(int user_id) throws SQLException {
        //статический ли? под вопросом
        PreparedStatement ps = Service_DB.getConnection().prepareStatement("SELECT * FROM users where user_id = ?;");
        ps.setInt(1, user_id);
        ResultSet rs = ps.executeQuery();
        User user = new User();
        while (rs.next()) {
            user.setUserId(user_id);
            user.setUserName(rs.getString("user_name"));
            user.setUserpick(rs.getString("userpic"));
            user.setPosition(rs.getString("position"));
            user.setPhone(rs.getLong("phone"));
            user.setEmail(rs.getString("e-mail"));
            user.setGender(rs.getBoolean("gender"));
            String directory = rs.getString("directory");
            if (directory != null) {
                user.setDirectory(rs.getString("directory").replace("\\\\", "\\"));
            } else {
                user.setDirectory("Рабочая папка не указана");
            }
        }
        Service_DB.getConnection().close();
        return user;
    }
}


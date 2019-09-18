import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Service_User_DB {
     static void setUserId(String userName) throws SQLException {
        PreparedStatement getUser = Service_DB.getConnection().prepareStatement("SELECT idUser FROM users WHERE user_name = ?;");
        getUser.setString(1, userName);
        ResultSet rsGetUser = getUser.executeQuery();
        while (rsGetUser.next()) {
            Current_User.setUserId(rsGetUser.getInt(1));
        }
        Service_DB.getConnection().close();
    }

     static String getUserPosition(int userId) throws SQLException {
        PreparedStatement getUserPosition = Service_DB.getConnection().prepareStatement("SELECT position FROM users WHERE idUser = ?;");
        getUserPosition.setInt(1, userId);
        ResultSet userPosition = getUserPosition.executeQuery();
        userPosition.next();
        Service_DB.getConnection().close();
        return userPosition.getString("position");
    }

     static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        PreparedStatement getAllUsersSt = Service_DB.getConnection().prepareStatement("SELECT user_name, idUser FROM users;");
        ResultSet getAllUsersRs = getAllUsersSt.executeQuery();
        while (getAllUsersRs.next()) {
            User_Lite temp = new User_Lite();
            temp.setUserId(getAllUsersRs.getInt("idUser"));
            temp.setUserName(getAllUsersRs.getString("user_name"));
            allUsers.add(temp);
        }
        Service_DB.getConnection().close();
        return allUsers;
    }
}

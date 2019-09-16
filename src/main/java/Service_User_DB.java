import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Service_User_DB {
    public static void loadUserId(String userName) throws SQLException {
        PreparedStatement getUser = Service_DB.getConnection().prepareStatement("SELECT idUser FROM users WHERE user_name = ?;");
        getUser.setString(1, userName);
        ResultSet rsGetUser = getUser.executeQuery();
        while (rsGetUser.next()) {
            Current_User.setUserId(rsGetUser.getInt(1));
        }
        Service_DB.getConnection().close();
    }

    public static ObservableList<User> getAllUsers() throws SQLException {
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

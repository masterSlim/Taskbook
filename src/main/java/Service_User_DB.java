import java.sql.*;

public class Service_User_DB {
    static String connectionUrl = "jdbc:mysql://127.0.0.1:3306/Taskbook? serverTimezone=UTC";
    static String serverPassword = "root";
    static String serverLogin = "root";
    public static void loadUserId(String userName) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl, serverLogin, serverPassword);
        PreparedStatement getUser = connection.prepareStatement("SELECT idUser FROM users WHERE username = ?;");
        getUser.setString(1, userName);
        ResultSet rsGetUser = getUser.executeQuery();
        while (rsGetUser.next()) {
            CurrentUser.setUserId(rsGetUser.getInt(1));
        }
        connection.close();
    }
}

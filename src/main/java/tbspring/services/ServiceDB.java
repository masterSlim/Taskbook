/*
package tbspring.services;

import java.sql.*;

public class ServiceDB {
    private static String serverLogin = "root";
    private static String serverPassword = "WG6Qkg59";
    private static String connectionUrl = "jdbc:mysql://127.0.0.1:3306/Taskbook? serverTimezone=UTC";

    */
/**
     * Метод проверяет соединение с сервером базы данных и
     *
     * @return boolean true при удачном подключении и false при его отсутствии
     *//*


    public static boolean testConnection() {
        try {
            Connection testConnection = getConnection();
            boolean opened = !testConnection.isClosed();
            testConnection.close();
            return opened;
        } catch (SQLException e) {
            System.err.println("Exception in test connection: " + e);
            return false;
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, serverLogin, serverPassword);
    }

    public static boolean tryLogin(String login, String password) {
        String rightPassword;
        try {
            Connection connection = getConnection();
            PreparedStatement tryLoginPs = connection.prepareStatement("SELECT password FROM users WHERE user_name = ?;");
            tryLoginPs.setString(1, login);
            ResultSet rsTryLogin = tryLoginPs.executeQuery();
            rsTryLogin.next();
            rightPassword = rsTryLogin.getString("password");
            if (password.equals(rightPassword)) {
                connection.close();
                return true;
            } else {
                System.out.println("Error in password");
                connection.close();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public void setServerLogin(String serverLogin) {
        //заготовка для окна настройки
        ServiceDB.serverLogin = serverLogin;
    }

    public void setServerPassword(String serverPassword) {
        //заготовка для окна настройки
        ServiceDB.serverPassword = serverPassword;
    }

    public void setServerConnectionUrl(String host, String port, String schema) {
        //заготовка для окна настройки
        connectionUrl = "jdbc:mysql://" + host.replaceAll(" ", "") + ":" + port.replaceAll(" ", "") + "/" + schema + "? serverTimezone=UTC";
    }

}




*/

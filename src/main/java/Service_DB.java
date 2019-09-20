import java.sql.*;

class Service_DB {
    private static String serverLogin = "root";
    private static String serverPassword = "root";
    private static String connectionUrl = "jdbc:mysql://127.0.0.1:3306/Taskbook? serverTimezone=UTC";

    //private static Connection connection = null;

    static boolean testConnection() throws SQLException {
        //метод устанавливающий соединение с сервером базы данных и возвращающий true при подключении
        // и false при неудаче
        Connection testConnection = getConnection();
        try (testConnection) {
            //возвращает противоположное значение от isClosed. Если isClosed = true - значит подключение
            // закрыто и всему методу нужно вернуть отрицательный ответ о подключении - false (т.е. !siClosed)
            return !testConnection.isClosed();
        } catch (Exception e) {
            testConnection.close();
            System.out.println(e);
            return false;
        } finally {
            testConnection.close();
            System.out.println("Test connection closed in testConnection finally");
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl, serverLogin, serverPassword);
        return connection;
    }


    public static boolean tryLogin(String login, String password) throws SQLException {
        //недописан
        String rightPassword;
        ;
        try {
            PreparedStatement tryLogin = getConnection().prepareStatement("SELECT password FROM users WHERE user_name = ?;");
            tryLogin.setString(1, login);
            ResultSet rsTryLogin = tryLogin.executeQuery();
            rsTryLogin.next();
            rightPassword = rsTryLogin.getString("password");
            if (password.equals(rightPassword)) {
                /*ниже - проба получить данные пользователя из базы данных отсюда
                PreparedStatement getUserStatement = connection.prepareStatement("SELECT * FROM users where username = ?;");
                ResultSet getUser = getUserStatement.executeQuery();*/
                getConnection().close();
                return true;
            } else {
                System.out.println("Error in password");
                getConnection().close();
                return false;
            }


        } catch (Exception e) {
            return false;
        }
    }

    public void setServerLogin(String serverLogin) {
        Service_DB.serverLogin = serverLogin;
    }

    public void setServerPassword(String serverPassword) {
        Service_DB.serverPassword = serverPassword;
    }

    public void setServerConnectionUrl(String host, String port, String schema) {
        connectionUrl = "jdbc:mysql://" + host.replaceAll(" ", "") + ":" + port.replaceAll(" ", "") + "/" + schema + "? serverTimezone=UTC";
    }

}




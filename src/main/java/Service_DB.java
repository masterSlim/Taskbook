import java.sql.*;

class Service_DB {
    private static String serverLogin = "root";
    private static String serverPassword = "root";
    private static String connectionUrl = "jdbc:mysql://127.0.0.1:3306/Taskbook? serverTimezone=UTC";

    private static Connection connection = null;

    static boolean testConnection() throws SQLException {
        //метод устанавливающий соединение с сервером базы данных и возвращающий true при подключении
        // и false при неудаче
        Connection testConnection = DriverManager.getConnection(connectionUrl, serverLogin, serverPassword);
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


    public static boolean tryLogin(String login, String password) throws SQLException {
        //недописан
        String rightPassword;
        connection = DriverManager.getConnection(connectionUrl, serverLogin, serverPassword);
        try {
            PreparedStatement tryLogin = connection.prepareStatement("SELECT password FROM users WHERE username = ?;");
            tryLogin.setString(1, login);
            ResultSet rsTryLogin = tryLogin.executeQuery();
            rsTryLogin.next();
            rightPassword = rsTryLogin.getString("password");
            if (password.equals(rightPassword)) {
                /*ниже - проба получить данные пользователя из базы данных отсюда
                PreparedStatement getUserStatement = connection.prepareStatement("SELECT * FROM users where username = ?;");
                ResultSet getUser = getUserStatement.executeQuery();*/
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

    public static void getTask() throws SQLException {
        //метод позволяет получить задачи для переданного пользователя
        //Connection connection = DriverManager.getConnection(connectionUrl, serverLogin, serverPassword);
        //PreparedStatement getTask = connection.prepareStatement("SELECT * from tasks where executor_id = ?;");
        //getTask.setInt(1, userId);

    }


    public void setServerLogin(String serverLogin) {
        this.serverLogin = serverLogin;
    }

    public void setServerPassword(String serverPassword) {
        this.serverPassword = serverPassword;
    }

    public void setServerConnectionUrl(String host, String port, String schema) {
        connectionUrl = "jdbc:mysql://" + host.replaceAll(" ", "") + ":" + port.replaceAll(" ", "") + "/" + schema + "? serverTimezone=UTC";
    }

}
// нужно сформировать запрос в таблицу по формированию записи
// *
       /* try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of books in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { *//*can't do anything *//* }
            try { stmt.close(); } catch(SQLException se) { *//*can't do anything *//* }
            try { rs.close(); } catch(SQLException se) { *//*can't do anything *//* }
        }*/




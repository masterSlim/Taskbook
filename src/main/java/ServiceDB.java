import java.sql.*;

class ServiceDB {
    private static String serverLogin = "root";
    private static String serverPassword = "root";
    private static String connectionUrl = "jdbc:mysql://127.0.0.1:3306/Taskbook? serverTimezone=UTC";

    public static void getTask(int userId) throws SQLException{
        //метод позволяет получить задачи для переданного пользователя
        Connection connection = DriverManager.getConnection(connectionUrl, serverLogin, serverPassword);
        PreparedStatement getTask = connection.prepareStatement("SELECT * from tasks where executor_id = ?;");
        getTask.setInt(1,userId);

    }

    public void setServerLogin(String serverLogin){
         this.serverLogin = serverLogin;
    }
    public void setServerPassword(String serverPassword){
        this.serverPassword = serverPassword;
    }
    public void setServerConnectionUrl(String host, String port, String schema){
        connectionUrl = "jdbc:mysql://" + host.replaceAll(" ","") +":" + port.replaceAll(" ","") + "/" + schema +"? serverTimezone=UTC";
    }

    static boolean tryConnect() throws SQLException {
        //метод устанавливающий соединение с сервером базы данных и возвращающий true при подключении
        // и false при неудаче
        Connection connection = DriverManager.getConnection(connectionUrl, serverLogin, serverPassword);
        try (connection) {
            //возвращает противоположное значение от isClosed. Если isClosed = true - значит подключение
            // закрыто и всему методу нужно вернуть отрицательный ответ о подключении - false (т.е. !siClosed)
            return !connection.isClosed();
        } catch (Exception e) {
            connection.close();
            System.out.println(e);
            return false;
        } finally {
            connection.close();
            System.out.println("Connection closed in Finally");
        }
    }
    public static void saveTasks(byte priority, int creatorId, String title, String task, int executorId, java.sql.Date createDate, java.sql.Date startDate, java.sql.Date closeDate, boolean isactive) throws SQLException{
        Connection connection = DriverManager.getConnection(connectionUrl, serverLogin,serverPassword);
        PreparedStatement saveTasks = connection.prepareStatement("INSERT INTO tasks (priority, creator_id, title, task, executor_id, create_datetime, start_datetime, close_datetime, isactive) values (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        saveTasks.setByte(1, priority);
        saveTasks.setInt(2, creatorId);
        saveTasks.setString(3, title);
        saveTasks.setString(4, task);
        saveTasks.setInt(5, executorId);
        saveTasks.setDate(6, createDate);
        saveTasks.setDate(7, startDate);
        saveTasks.setDate(8, closeDate);
        saveTasks.setBoolean(9, isactive);
        try{
            saveTasks.executeUpdate();
            System.out.println("Success");
        }catch(SQLException e){
            System.out.println(e);
        }
        connection.close();
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




import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service_Task_DB {
    private static String serverLogin = "root";
    private static String serverPassword = "root";
    private static String connectionUrl = "jdbc:mysql://127.0.0.1:3306/Taskbook? serverTimezone=UTC";

    public static void saveTasks(byte priority, int creatorId, String title, String task, int executorId, java.sql.Date createDate, java.sql.Date startDate, java.sql.Date deadline, boolean isactive) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl, serverLogin, serverPassword);
        PreparedStatement saveTasks = connection.prepareStatement("INSERT INTO tasks (priority, creator_id, title, task, executor_id, create_datetime, start_datetime, deadline, isactive) values (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        saveTasks.setByte(1, priority);
        saveTasks.setInt(2, creatorId);
        saveTasks.setString(3, title);
        saveTasks.setString(4, task);
        saveTasks.setInt(5, executorId);
        saveTasks.setDate(6, createDate);
        saveTasks.setDate(7, startDate);
        saveTasks.setDate(8, deadline);
        saveTasks.setBoolean(9, isactive);
        try {
            saveTasks.executeUpdate();
            System.out.println("Success");
        } catch (SQLException e) {
            System.out.println(e);
        }
        connection.close();
    }

    public static ObservableList<Object[]> getTasksLite(int userId) throws SQLException {
        Connection connection = DriverManager.getConnection(connectionUrl, serverLogin, serverPassword);
        try {
            PreparedStatement getTasksLite = connection.prepareStatement("SELECT task_id, title, deadline FROM tasks WHERE executor_id =? AND isactive = ? ;");
            getTasksLite.setInt(1, userId);
            getTasksLite.setBoolean(2, true);
            ResultSet tasksLite = getTasksLite.executeQuery();
            ObservableList<Object[]> taskObservableList = FXCollections.observableArrayList();
                for (int i = 1; i < tasksLite.getMetaData().getColumnCount(); i++) {
                    tasksLite.next();
                    taskObservableList.add(new Object[]{tasksLite.getInt("task_id"), tasksLite.getString("title"), tasksLite.getDate("deadline")});
                    System.out.println(Arrays.deepToString(taskObservableList.get(i - 1)));
                }
                return taskObservableList;
        } catch (Exception e) {
            System.out.println("Exception in getTaskLite " + e);
        }
        connection.close();
        return null;
    }
}

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Service_Task_DB {

    public static void saveTasks(byte priority, int creatorId, String title, String task, int executorId, java.sql.Date createDate, java.sql.Date startDate, java.sql.Date deadline, boolean isactive) throws SQLException {
        PreparedStatement saveTasks = Service_DB.getConnection().prepareStatement("INSERT INTO tasks (priority, creator_id, title, task, executor_id, create_datetime, start_datetime, deadline, isactive) values (?, ?, ?, ?, ?, ?, ?, ?, ?);");
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
        Service_DB.getConnection().close();
    }

    public static ObservableList<Task_Lite> getTasksLite(int userId) throws SQLException {
        try {
            PreparedStatement getTasksLite = Service_DB.getConnection().prepareStatement("SELECT task_id, title, deadline FROM tasks WHERE executor_id =? AND isactive = ? ;");
            getTasksLite.setInt(1, userId);
            getTasksLite.setBoolean(2, true);
            ResultSet rsTasksLite = getTasksLite.executeQuery();
            ObservableList<Task_Lite> taskObservableList = FXCollections.observableArrayList();
                while (rsTasksLite.next()) {
                    //https://javarush.ru/groups/posts/1708-unikaljhnoe-imja-dlja-obhhekta
                    Task_Lite temp = new Task_Lite();
                    temp.setTaskLite(rsTasksLite.getInt("task_id"), rsTasksLite.getString("title"), rsTasksLite.getDate("deadline"));
                    taskObservableList.add(temp);
                }
            return taskObservableList;
        } catch (Exception e) {
            System.out.println("Exception in getTaskLite " + e);
        }
        Service_DB.getConnection().close();
        return null;
    }
}

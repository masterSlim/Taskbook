package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ServiceDBTask {

    public static void saveTasks(byte priority, long creatorId, String title, String task, long executorId, java.sql.Date createDate, java.sql.Date startDate, java.sql.Date deadline, boolean isactive) throws SQLException {
        Connection connection = ServiceDB.getConnection();
        PreparedStatement saveTasks = connection.prepareStatement("INSERT INTO tasks (priority, creator_id, title, task, executor_id, create_datetime, start_datetime, deadline, is_active) values (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        saveTasks.setByte(1, priority);
        saveTasks.setLong(2, creatorId);
        saveTasks.setString(3, title);
        saveTasks.setString(4, task);
        saveTasks.setLong(5, executorId);
        saveTasks.setDate(6, createDate);
        saveTasks.setDate(7, startDate);
        saveTasks.setDate(8, deadline);
        saveTasks.setBoolean(9, isactive);
        try {
            saveTasks.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        connection.close();
    }

    public static ObservableList<Task> getTasksExecutor(long userId) throws SQLException {
        Connection connection = ServiceDB.getConnection();
        PreparedStatement getTasks = connection.prepareStatement("SELECT task_id, title, deadline FROM tasks WHERE executor_id =? AND is_active = ? ;");
        getTasks.setLong(1, userId);
        getTasks.setBoolean(2, true);
        ResultSet rsTasks = getTasks.executeQuery();
        ObservableList<Task> taskObservableList = FXCollections.observableArrayList();
        while (rsTasks.next()) {

            Task temp = new Task(rsTasks.getInt("task_id"), rsTasks.getString("title"), rsTasks.getDate("deadline"));
            taskObservableList.add(temp);
        }
        connection.close();
        return taskObservableList;
    }

    public static ObservableList<Task> getTasksManager(long userId) throws SQLException {
        Connection connection = ServiceDB.getConnection();
        PreparedStatement getTasks = connection.prepareStatement("SELECT task_id, title, deadline FROM tasks WHERE creator_id =?;");
        getTasks.setLong(1, userId);
        ResultSet rsTasks = getTasks.executeQuery();
        ObservableList<Task> taskList = FXCollections.observableArrayList();
        while (rsTasks.next()) {
            int taskId = rsTasks.getInt("task_id");
            String title = rsTasks.getString("title");
            Date date = rsTasks.getDate("deadline");
            Task temp = new Task(taskId, title, date);
            taskList.add(temp);
        }
        connection.close();
        return taskList;
    }
}

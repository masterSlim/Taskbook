package controllers.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tbspring.models.ActiveUser;
import tbspring.models.User;
import tbspring.services.ServiceDB;
import tbspring.services.ServiceDBUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentTaskController {
    private int taskId;
    private ActiveUser activeUser;
    @FXML
    private HBox hboxExecutors;
    @FXML
    private Label taskTitle;
    @FXML
    private Text task;
    @FXML
    private Label deadline;
    @FXML
    private ImageView priorityImageView;
    @FXML
    private Button closeTask;
    @FXML
    private Hyperlink deleteTask;

    public CurrentTaskController(ActiveUser activeUser, int taskId) {
        this.activeUser = activeUser;
        this.taskId = taskId;
    }


    public void initialize() throws SQLException, IOException {
        PreparedStatement currentTaskPs = ServiceDB.getConnection().prepareStatement("SELECT * FROM tasks where task_id =?;");
        currentTaskPs.setInt(1, taskId);
        ResultSet currentTaskRs = currentTaskPs.executeQuery();
        currentTaskRs.next();
            taskTitle.setText(currentTaskRs.getString("title"));
            task.setText(currentTaskRs.getString("task"));
            deadline.setText(currentTaskRs.getDate("deadline").toString());
            if (currentTaskRs.getByte("priority") == (byte) 1) {
                priorityImageView.setImage(new Image("/icons/priority_high.png"));
                taskTitle.setTextFill(Color.RED);
            }
            //оборажаются исполнители для задачи
            User user = ServiceDBUser.getUser(currentTaskRs.getInt("executor_id"));
            UserNamePane userPane = new UserNamePane(activeUser, user);
            hboxExecutors.getChildren().add(userPane);

            if (currentTaskRs.getBoolean("is_closed")) {
                closeTask.setDisable(true);
                closeTask.setText("Задача закрыта");
            }
    }

    public void closeTask(MouseEvent mouseEvent) throws SQLException {
        PreparedStatement currentTaskPs = ServiceDB.getConnection().prepareStatement("UPDATE tasks SET is_active=?, is_closed = ? WHERE task_id=?");
        currentTaskPs.setBoolean(1, false);
        currentTaskPs.setBoolean(2, true);
        currentTaskPs.setInt(3, taskId);
        currentTaskPs.executeUpdate();
        closeTask.setDisable(true);
        closeTask.setText("Задача закрыта");
    }

    public void deleteTask(MouseEvent mouseEvent) throws SQLException {
        PreparedStatement currentTaskPs = ServiceDB.getConnection().prepareStatement("DELETE FROM tasks WHERE task_id=?");
        currentTaskPs.setInt(1, taskId);
        currentTaskPs.executeUpdate();
        MainStageController.nextStage.close();

    }

    public void editTask(MouseEvent mouseEvent) {
    }
}

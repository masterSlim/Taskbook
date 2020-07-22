package controllers.javafx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tbspring.models.ActiveUser;
import tbspring.models.Task;
import tbspring.models.User;
import tbspring.services.ServiceDBTask;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

public class UserInfoController {
    private User user;
    private ActiveUser activeUser;
    @FXML
    private TableColumn<Task, String> columnTaskTitle;
    @FXML
    private TableColumn<Task, Date> columnDeadline;
    @FXML
    private TableColumn<Task, Integer> columnTaskId;
    @FXML
    private TableView<Task> tableTasks;
    @FXML
    private Label labelUserName;
    @FXML
    private ImageView userpic;
    @FXML
    private Label labelDirectory;
    @FXML
    private Label labelPosition;
    @FXML
    private Label labelPhone;
    @FXML
    private Label labelEmail;

    public UserInfoController(ActiveUser activeUser, User user) {
        this.activeUser = activeUser;
        this.user = user;
    }

    public void initialize() throws SQLException {
        labelUserName.setText(user.getUserName());
        //userpic.setImage(new Image(user.getUserpick()));
        labelDirectory.setText(user.getDirectory());
        labelEmail.setText(user.getEmail());
        labelPhone.setText(Arrays.toString(user.getPhone()));
        labelPosition.setText(user.getPosition().toString());
        columnTaskId.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        columnTaskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        tableTasks.getItems().clear();
        ObservableList<Task> tasksCurrentUser = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                ServiceDBTask.getTasksManager(activeUser.getUserId()) :
                ServiceDBTask.getTasksExecutor(activeUser.getUserId());
        tableTasks.getItems().clear();
        tableTasks.setItems(tasksCurrentUser);
    }

    @FXML
    public void openTask() throws IOException {
        if (tableTasks.getSelectionModel().getSelectedItem() != null) {
            //если выбрана не пустая строка, то в соответствии с position пользователя открывается окно задачи
            int selectedTaskId = tableTasks.getSelectionModel().getSelectedItem().getTaskId();
            CurrentTaskController controller = new CurrentTaskController(activeUser, selectedTaskId);
            String path = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                    "/fxml/CurrentTaskManager.fxml" :
                    "/fxml/CurrentTaskExecutor.fxml";
            Stage stageNewTask = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            loader.setController(controller);
            Scene openTask = new Scene(loader.load());
            stageNewTask.setScene(openTask);
            stageNewTask.show();
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
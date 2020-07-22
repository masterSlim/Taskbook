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

public class MainStageController {
    @FXML
    static Stage nextStage;
    @FXML
    public TableView<Task> tableTaskLite;
    @FXML
    public TableColumn<Task, Integer> columnId;
    @FXML
    public TableColumn<Task, String> columnTaskTitle;
    @FXML
    public TableColumn<Task, Date> columnDeadline;
    private ActiveUser activeUser;
    @FXML
    private Label user;
    @FXML
    private ImageView refreshButton;
    @FXML
    private ImageView logOutId;

    public MainStageController(ActiveUser activeUser) {
        this.activeUser = activeUser;
    }

    public static void close() {
        nextStage.close();
    }

    @FXML
    void refreshTable() throws SQLException {
        tableTaskLite.getItems().clear();
        ObservableList<Task> tasksCurrentUser = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                ServiceDBTask.getTasksManager(activeUser.getUserId()) :
                ServiceDBTask.getTasksExecutor(activeUser.getUserId());
        tableTaskLite.getItems().clear();
        tableTaskLite.setItems(tasksCurrentUser);
    }

    @FXML
    public void logOut() {
        Stage stage = (Stage) logOutId.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void initialize() throws SQLException {
        //устанавливается пользователь введённый и переданный controllers.javafx.LoginController
        user.setText(activeUser.getUserName());
        columnId.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        columnTaskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        refreshTable();
    }

    @FXML
    public void openTask() throws IOException {
        if (tableTaskLite.getSelectionModel().getSelectedItem() != null) {
            //если выбрана не пустая строка, то в соответствии с position пользователя открывается окно задачи
            int selectedTaskId = tableTaskLite.getSelectionModel().getSelectedItem().getTaskId();
            CurrentTaskController controller = new CurrentTaskController(activeUser, selectedTaskId);
            String path = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                    "/fxml/CurrentTaskManager.fxml" :
                    "/fxml/CurrentTaskExecutor.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            loader.setController(controller);
            nextStage = new Stage();
            Scene openTask = new Scene(loader.load());
            nextStage.setScene(openTask);
            nextStage.show();
        }
    }

    @FXML
    private void newTask() throws IOException {
        /*при нажатии на большой знак + в приложении открывается сцена постановки новой задачи NewTaskManager.fxml и управление
        передаётся на его контроллер*/
        FXMLLoader loader;
        NewTaskController controller = new NewTaskController(activeUser);
        //controller.setTaskId(tableTaskLite.getSelectionModel().getSelectedItem().getTaskId());
        String path = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                "/fxml/NewTaskManager.fxml" :
                "/fxml/NewTaskExecutor.fxml";
        loader = new FXMLLoader(getClass().getResource(path));
        loader.setController(controller);
        nextStage = new Stage();
        Scene scene = new Scene(loader.load());
        nextStage.setScene(scene);
        nextStage.show();
    }

    @FXML
    public void settings() throws IOException {
        String path = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                "/fxml/SettingsManager.fxml" :
                "/fxml/SettingsExecutor.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        SettingsController controller = new SettingsController(activeUser);
        loader.setController(controller);
        Scene settings = new Scene(loader.load());
        nextStage = new Stage();
        nextStage.setScene(settings);
        nextStage.show();
    }
}

package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.ActiveUser;
import models.Task;
import models.User;
import services.ServiceDBTask;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class MainStageController {
    @FXML
    static Stage stageNewTask = new Stage();
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
        stageNewTask.close();
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
        close();
       //this.show();
    }

    @FXML
    public void initialize() throws SQLException {
        //устанавливается пользователь введённый и переданный controllers.LoginController
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
            CurrentTaskController.selected = tableTaskLite.getSelectionModel().getSelectedItem().getTaskId();
            String path = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                    "/fxml/Current_Task_Manager.fxml" :
                    "/fxml/Current_Task_Executor.fxml";
            Scene openTask = new Scene((FXMLLoader.load(getClass().getResource(path))));
            stageNewTask.setScene(openTask);
            stageNewTask.show();
        }
    }

    @FXML
    private void newTask() throws IOException {
        /*при нажатии на большой знак + в приложении открывается сцена постановки новой задачи New_Task_Manager.fxml и управление
        передаётся на его контроллер*/
        FXMLLoader loader;
        NewTaskController controller = new NewTaskController(activeUser);
        //controller.setTaskId(tableTaskLite.getSelectionModel().getSelectedItem().getTaskId());
        String path = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                "/fxml/New_Task_Manager.fxml" :
                "/fxml/New_Task_Executor.fxml";
        loader = new FXMLLoader(getClass().getResource(path));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stageNewTask.setScene(scene);
        stageNewTask.show();
    }

    public void settings(MouseEvent mouseEvent) throws IOException {
        String path = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                "/fxml/Settings_Manager.fxml" :
                "/fxml/Settings_Executor.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        SettingsController controller =new SettingsController(activeUser);
        loader.setController(controller);
        Scene settings = new Scene(loader.load());
        stageNewTask.setScene(settings);
        stageNewTask.show();
    }
}

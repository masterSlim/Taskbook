package controllers;

import controllers.Current_Task_Controller;
import javafx.collections.FXCollections;
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
import models.Current_User;
import models.Task_Lite;
import models.User;
import services.Service_Task_DB;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class User_info_Controller {
    private User user;
    @FXML
    private TableColumn<Task_Lite, String> columnTaskTitle;
    @FXML
    private TableColumn<Task_Lite, Date> columnDeadline;
    @FXML
    private TableColumn<Task_Lite, Integer> columnTaskId;
    @FXML
    private TableView<Task_Lite> tableTasks;
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

    public void initialize() throws SQLException {
        labelUserName.setText(user.getUserName());
        //userpic.setImage(new Image(user.getUserpick()));
        labelDirectory.setText(user.getDirectory());
        labelEmail.setText(user.getEmail());
        labelPhone.setText(Long.toString(user.getPhone()));
        labelPosition.setText(user.getPosition());
        columnTaskId.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        columnTaskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        tableTasks.getItems().clear();
        if (Current_User.getPosition().equals("Руководитель")) {
            ObservableList<Task_Lite> tasksCurrentUser = Service_Task_DB.getTasksMngrLite(Current_User.getUserId());
            tableTasks.getItems().clear();
            tableTasks.setItems(tasksCurrentUser);
        } else {
            ObservableList<Task_Lite> tasksCurrentUser = Service_Task_DB.getTasksExctrLite(Current_User.getUserId());
            tableTasks.getItems().clear();
            tableTasks.setItems(tasksCurrentUser);
        }

    }
    @FXML
    public void openTask() throws IOException {
        if (tableTasks.getSelectionModel().getSelectedItem() != null) {
            //если выбрана не пустая строка, то в соответствии с position пользователя открывается окно задачи
            Current_Task_Controller.selected = tableTasks.getSelectionModel().getSelectedItem().getTaskId();
            if (Current_User.getPosition().equals("Руководитель")) {
                Stage stageNewTask = new Stage();
                Scene openTask = new Scene((FXMLLoader.load(getClass().getResource("/fxml/Current_Task_Manager.fxml"))));
                stageNewTask.setScene(openTask);
                stageNewTask.show();
            } else {
                Stage stageNewTask = new Stage();
                Scene openTask = new Scene((FXMLLoader.load(getClass().getResource("/fxml/Current_Task_Executor.fxml"))));
                stageNewTask.setScene(openTask);
                stageNewTask.show();
            }
        } else {//если выбрана пустая строка, то ничего не происходит
        }
    }
    public void setUser(User user) {
        this.user = user;
    }
}

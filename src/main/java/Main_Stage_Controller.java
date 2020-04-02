import javafx.collections.FXCollections;
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
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class Main_Stage_Controller {
    @FXML
    static Stage stageNewTask = new Stage();
    @FXML
    public TableView<Task_Lite> tableTaskLite;
    @FXML
    public TableColumn<Task_Lite, Integer> columnId;
    @FXML
    public TableColumn<Task_Lite, String> columnTaskTitle;
    @FXML
    public TableColumn<Task_Lite, Date> columnDeadline;
    //int userId;
    @FXML
    private Label user;
    @FXML
    private ImageView refreshButton;
    @FXML
    private ImageView logOutId;

    public static void close() {
        stageNewTask.close();
    }

    @FXML
    void refreshTable() throws SQLException{
        tableTaskLite.getItems().clear();
        if (Current_User.getPosition().equals("Руководитель")) {
            ObservableList<Task_Lite> tasksCurrentUser = FXCollections.observableArrayList(Service_Task_DB.getTasksMngrLite(Current_User.getUserId()));
            tableTaskLite.getItems().clear();
            tableTaskLite.setItems(tasksCurrentUser);
        } else {
            ObservableList<Task_Lite> tasksCurrentUser = Service_Task_DB.getTasksExctrLite(Current_User.getUserId());
            tableTaskLite.getItems().clear();
            tableTaskLite.setItems(tasksCurrentUser);
        }

    }

    @FXML
    public void logOut() {
        Login_Controller.close();
        Main.login.show();
    }

    @FXML
    public void initialize() throws SQLException{
        //устанавливается пользователь введённый и переданный Login_Controller
        user.setText(Current_User.getUserName());
        columnId.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        columnTaskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        refreshTable();
    }

    @FXML
    public void openTask() throws IOException {
        if (tableTaskLite.getSelectionModel().getSelectedItem() != null) {
            //если выбрана не пустая строка, то в соответствии с position пользователя открывается окно задачи
            Current_Task_Controller.selected = tableTaskLite.getSelectionModel().getSelectedItem().getTaskId();
            if (Current_User.getPosition().equals("Руководитель")) {
                Scene openTask = new Scene((FXMLLoader.load(getClass().getResource("Current_Task_Manager.fxml"))));
                stageNewTask.setScene(openTask);
                stageNewTask.show();
            } else {
                Scene openTask = new Scene((FXMLLoader.load(getClass().getResource("Current_Task_Executor.fxml"))));
                stageNewTask.setScene(openTask);
                stageNewTask.show();
            }
        } else {//если выбрана пустая строка, то ничего не происходит
        }
    }

    @FXML
    private void newTask() throws IOException {
        /*при нажатии на большой знак + в приложении открывается сцена постановки новой задачи New_Task_Manager.fxml и управление
        передаётся на его контроллер*/
        New_Task_Controller controller = new New_Task_Controller();
        //controller.setTaskId(tableTaskLite.getSelectionModel().getSelectedItem().getTaskId());
        FXMLLoader loader;
        if(Current_User.getPosition().equals("Руководитель")) {
             loader = new FXMLLoader(getClass().getResource("New_Task_Manager.fxml"));
        } else {
             loader = new FXMLLoader(getClass().getResource("New_Task_Executor.fxml"));
        }
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Scene sceneNewTask = new Scene(loader.load());
        stageNewTask.setScene(sceneNewTask);
        stageNewTask.show();
    }

    public void settings(MouseEvent mouseEvent) throws IOException {
        if (Current_User.getPosition().equals("Руководитель")) {
            Scene openTask = new Scene((FXMLLoader.load(getClass().getResource("Settings_Manager.fxml"))));
            stageNewTask.setScene(openTask);
            stageNewTask.show();
        } else {
            Scene settings = new Scene((FXMLLoader.load(getClass().getResource("Settings_Executor.fxml"))));
            stageNewTask.setScene(settings);
            stageNewTask.show();
        }
    }
}

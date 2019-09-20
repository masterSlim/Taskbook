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

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class Main_Stage_Manager_Controller {
    @FXML
    static Stage stageNewTask = new Stage();
    @FXML
    private TableView<Task_Lite> tableTaskLite;
    @FXML
    private TableColumn<Task_Lite, Integer> columnId;
    @FXML
    private TableColumn<Task_Lite, String> columnTaskTitle;
    @FXML
    private TableColumn<Task_Lite, Date> columnDeadline;
    @FXML
    private Label user;
    @FXML
    private ImageView logOutId;

    @FXML
    public void refreshTable() throws SQLException {
        ObservableList<Task_Lite> tasksCurrentUser = Service_Task_DB.getTasksMngrLite(Current_User.getUserId());
        tableTaskLite.setItems(tasksCurrentUser);
    }

    @FXML
    public void logOut() {
        Login_Controller.close();
        Main.login.show();
    }

    @FXML
    public void initialize() throws SQLException, IOException {
        //устанавливается пользователь введённый и переданный Login_Controller
        user.setText(Current_User.getUserName());
        columnId.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        columnTaskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        ObservableList<Task_Lite> tasksCurrentUser = Service_Task_DB.getTasksMngrLite(Current_User.getUserId());
        tableTaskLite.setItems(tasksCurrentUser);
    }
    @FXML
    public void openTask() throws IOException {
        Current_Task_Manager_Controller.selected = tableTaskLite.getSelectionModel().getSelectedItem().getTaskId();
        Scene openTask = new Scene((FXMLLoader.load(getClass().getResource("Current_Task_Manager.fxml"))));
        stageNewTask.setScene(openTask);
        stageNewTask.show();
    }

    @FXML
    private void newTask() throws IOException {
        /*при нажатии на большой знак + в приложении открывается сцена постановки новой задачи New_Task.fxml и управление
        передаётся на его контроллер*/
        Scene sceneNewTask = new Scene(FXMLLoader.load(getClass().getResource("New_Task.fxml")));
        stageNewTask.setScene(sceneNewTask);
        stageNewTask.show();
    }
}

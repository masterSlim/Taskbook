import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

public class Main_Stage_Controller {
    @FXML
    static Stage stageNewTask = new Stage();
    @FXML
    private TableColumn<Integer, Integer> columnId;
    @FXML
    private TableColumn columnTaskTitle;
    @FXML
    private TableColumn columnDeadline;
    @FXML
    private Label user;
    @FXML
    private TableView tableTaskLite;
    private ObservableList<Task> userTask = FXCollections.observableArrayList();

    public void logOut() {
    }

    @FXML
    public void initialize() throws SQLException {
        //устанавливается пользователь введённый и переданный Login_Controller
        user.setText(CurrentUser.getUserName());       ;
        //tableTaskLite.setItems(Service_Task_DB.getTasksLite(CurrentUser.getUserId()));

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTaskTitle.setCellValueFactory(new PropertyValueFactory<String, String>("title"));
        columnDeadline.setCellValueFactory(new PropertyValueFactory<Date, Date>("deadline"));
        ObservableList<Object[]> tasksCurrentUser = Service_Task_DB.getTasksLite(CurrentUser.getUserId());
        //int test = tasksCurrentUser.size();
        for(int i = 1; i <= tasksCurrentUser.size(); i++){
            Object[] a = tasksCurrentUser.get(i-1);
            int id = Integer.parseInt(a[0].toString());
            String title = a[1].toString();
            Date deadline = Date.valueOf(a[2].toString());
            !!! нужно заносить данные в строку таблицы во время прохождения цикла!!!
            System.out.println(id);
            System.out.println(title);
            System.out.println(deadline);
        }
        //user.setText(userName);
        //пока таблица users пустая, преобразовываю имя пользователя в Id для тестов
        //int userId = ServiceDB.tryLogin().;
        //ServiceDB.getTask(userId);
    }

    @FXML
    private void newTask() throws IOException {
        /*при нажатии на большой знак + в приложении открывается сцена постановки новой задачи New_Task.fxml и управление
        передаётся на его контроллер*/
        Scene sceneNewTask = new Scene(FXMLLoader.load(getClass().getResource("New_Task.fxml")));
        stageNewTask.setScene(sceneNewTask);
        stageNewTask.show();
        //rootPane.getChildren().setAll(pane);
    }
}

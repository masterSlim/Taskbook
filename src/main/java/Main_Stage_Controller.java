import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class Main_Stage_Controller {
    public static String userName;
    @FXML
    static Stage stageNewTask = new Stage();
    // ServiceDB serviceDB = new ServiceDB(); - экземпляр класса отладки// — зачем??
    @FXML
    private Button buttonOk;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonLater;
    @FXML
    private Label loginStatus;
    @FXML
    private Label user;
    @FXML
    //обязательно нужно указать данный fx:id для AnchorPane в самом файле разметки .fxml
    private AnchorPane rootPane;


    public void logOut() {
    }

    @FXML
    public void initialize()throws SQLException {
        //устанавливается пользователь введённый и переданный Login_Controller
        user.setText(userName);
        //пока таблица users пустая, преобразовываю имя пользователя в Id для тестов
        int userId = parseInt(userName);
        ServiceDB.getTask(userId);
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
class CurrentUser extends User{

        }

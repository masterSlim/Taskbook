package controllers.javafx;

import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tbspring.models.ActiveUser;
import tbspring.models.User;
import tbspring.services.ServiceDB;
import tbspring.services.ServiceDBUser;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    public TextField fldLogin;
    @FXML
    public PasswordField fldPassword;
    ActiveUser activeUser;
    @FXML
    Label connectionStatus;

    @FXML
    private Stage stageMain;

    @FXML
    private Button btnLogin;

    private void checkConnection() {
        //пишет в нижней части окна есть ли соединение с сервером
        try {
            connectionStatus.setText((ServiceDB.testConnection()) ?
                    "Подключено к серверу" : "Подключение отсутствует");
        } catch (Exception e) {
            connectionStatus.setText("Подключение отсутствует");
        }
    }

    public void initialize() {
        //проверка соединения с сервером, и сообщениние об этом пользователю
        checkConnection();
    }

    private boolean tryLogin() {
        String login = fldLogin.getText();
        String password = fldPassword.getText();
        return ServiceDB.tryLogin(login, password);
    }

    @FXML
    private void login() throws SQLException, IOException {
        //по нажатию на кнопку "Войти" сравнивается введёное в fldLogin с логином и паролем в базе данных
        if (tryLogin()) {
            //если всё введено правильно, в currentUser передаётся имя пользователя из введённого в поле fldLogin
            //из базы данных получаем userId по переданному ранее userName и передаём его в ActiveUser
            //управление передаётся другому контроллеру, указанному в MainStageExecutor.fxml
            activeUser = ServiceDBUser.setActiveUser(fldLogin.getText());
            /* если авторизовавшийся пользователь - руководитель, то открывается окно
            со списком задач Main_Stage_Manager_Controller*/
            String path = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                    "/fxml/MainStageManager.fxml" : "/fxml/MainStageExecutor.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            MainStageController controller = new MainStageController(activeUser);
            loader.setController(controller);
            stageMain = new Stage();
            Scene mainScene = new Scene(loader.load());
            stageMain.setScene(mainScene);
            fldPassword.clear();
            fldLogin.clear();
            //окно ввода логина и пароля закрывается
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
            //открывается Main Stage
            stageMain.show();
        } else {
            //если хотя-бы одно из полей введено неправильно срабатывает else, которое выводит в консоль сообщение и очищает поля
            fldLogin.clear();
            fldPassword.clear();
            System.out.println("Wrong Login/Password");
        }

    }
}
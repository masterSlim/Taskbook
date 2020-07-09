package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.ActiveUser;
import models.User;
import services.ServiceDB;
import services.ServiceDBUser;

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
    private String login;
    @FXML
    private Stage stageMain = new Stage();
    @FXML
    private Button btnLogin;

    private void connection() {
        //пишет в нижней части окна есть ли соединение с сервером
        try {
            boolean connect = ServiceDB.testConnection();
            if (connect) {
                connectionStatus.setText("Подключено к серверу");
            }
        } catch (Exception e) {
            connectionStatus.setText("Подключение отсутствует");
        }
    }

    public void close() {
        stageMain.close();
    }

    public void initialize() {
        //проверка соединения с сервером, и сообщениние об этом пользователю
        connection();
    }

    private boolean tryLogin() {
        String login = fldLogin.getText();
        String password = fldPassword.getText();
        return ServiceDB.tryLogin(login, password);
    }


    @FXML
    private void login() throws Exception {
        //по нажатию на кнопку "Войти" сравнивается введёное в fldLogin с логином и паролем в базе данных
        if (tryLogin()) {
            //если всё введено правильно, в currentUser передаётся имя пользователя из введённого в поле fldLogin
            //из базы данных получаем userId по переданному ранее userName и передаём его в ActiveUser
            //управление передаётся другому контроллеру, указанному в Main_Stage_Executor.fxml
            activeUser = ServiceDBUser.setActiveUser(fldLogin.getText());
            /* если авторизовавшийся пользователь - руководитель, то открывается окно
            со списком задач Main_Stage_Manager_Controller*/
            String path = (activeUser.getPosition() == User.POSITION.MANAGER) ?
                    "/fxml/Main_Stage_Manager.fxml" : "/fxml/Main_Stage_Executor.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            MainStageController controller = new MainStageController(activeUser);
            loader.setController(controller);
            Scene mainScene = new Scene(loader.load());
            stageMain.setScene(mainScene);
            fldPassword.clear();
            fldLogin.clear();
            //окно ввода логина и пароля закрывается
            close();
            //открывается Main Stage
            stageMain.show();
        } else {
            //если хотя-бы одно из полей введено неправильно срабатывает else, которое выводит в консоль сообщение и очищает поля
            fldLogin.clear();
            fldPassword.clear();
            System.out.println("Wrong Login/Password");
        }

    }

    public void mouseDrag(MouseEvent mouseEvent) {

    }
}

/*    @FXML
    private void checkLoginPassword() throws Exception {
        String login = fldLogin.getText();
        String password = fldPassword.getText();
        login(login, password);
    }*/

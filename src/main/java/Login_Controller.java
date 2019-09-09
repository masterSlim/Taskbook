import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Login_Controller {

    @FXML
    public static String login;
    @FXML
    public Stage stageMain = new Stage();
    @FXML
    Label connectionStatus;
    @FXML
    private TextField fldLogin;
    @FXML
    private PasswordField fldPassword;
    @FXML
    private Button btnLogin;

    void connection() throws SQLException {
        if (Service_DB.testConnection()) {
            connectionStatus.setText("Подключено к серверу");
        } else {
            connectionStatus.setText("Подключение отсутствует");
        }
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        connection();
    }

    @FXML
    private void login() throws Exception {
        //метод, вызываемый по нажатию кнопки "Войти". Сейчас сравнивает правильность введённого логина и пароля с установленными непосредственно в методе
        //String rightLogin = "1";
        //String rightPassword = "1";
        //сравнивается введёное в fldLogin с локальной переменной rghtLogin
        Boolean login = Service_DB.tryLogin(fldLogin.getText(), fldPassword.getText());
        if (login) {
            //если всё введено правильно, в CurrentUser передаётся имя пользователя из введённого в поле fldLogin
            CurrentUser.setUserName(fldLogin.getText());
            //из базы данных получаем userId по переданному ранее userName и передаём его в CurrentUser
            Service_User_DB.loadUserId(CurrentUser.getUserName());
            //управление передаётся другому контроллеру, указанному в Main_Stage.fxml
            Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("Main_Stage.fxml")));
            stageMain.setScene(mainScene);
            //окно ввода логина и пароля закрывается
            Main.stage.close();
            //открывается Main Stage
            stageMain.show();
        } else {
            //если хотя-бы одно из полей введено неправильно срабатывает else, которое выводит в консоль сообщение
            fldLogin.setText("");
            fldPassword.setText("");
            System.out.println("Wrong Login/Password");
        }

    }

    public void mouseDrag(MouseEvent mouseEvent) {

    }

    public void close(MouseEvent mouseEvent) {
    }
}

/*    @FXML
    private void checkLoginPassword() throws Exception {
        String login = fldLogin.getText();
        String password = fldPassword.getText();
        login(login, password);
    }*/

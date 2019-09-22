import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Login_Controller {

    @FXML
    static String login;
    private static Stage stageMain = new Stage();
    @FXML
    public TextField fldLogin;
    @FXML
    public PasswordField fldPassword;
    @FXML
    Label connectionStatus;
    @FXML
    private Button btnLogin;

    public static void close() {
        stageMain.close();
    }

    private void connection() {
        //пишет в нижней части окна есть ли соединение с сервером
        try {
            boolean connect = Service_DB.testConnection();
            if (connect) {
                connectionStatus.setText("Подключено к серверу");
            }
        } catch (Exception e) {
            connectionStatus.setText("Подключение отсутствует");
        }
    }

    public void initialize() {
        //проверка соединения с сервером, и сообщениние об этом пользователю
        connection();
    }

    @FXML
    private void login() throws Exception {
        //по нажатию на кнопку "Войти" сравнивается введёное в fldLogin с логином и паролем в базе данных
        boolean login = Service_DB.tryLogin(fldLogin.getText(), fldPassword.getText());
        if (login) {
            //если всё введено правильно, в currentUser передаётся имя пользователя из введённого в поле fldLogin
            //из базы данных получаем userId по переданному ранее userName и передаём его в CurrentUser
            //управление передаётся другому контроллеру, указанному в Main_Stage_Executor.fxml
            Service_User_DB.setUser(fldLogin.getText());
            if (Current_User.getPosition().equals("Руководитель")) {
                /* если авторизовавшийся пользователь - руководитель, то открывается окно
                со списком задач Main_Stage_Manager_Controller*/
                Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("Main_Stage_Manager.fxml")));
                stageMain.setScene(mainScene);
                fldPassword.clear();
                fldLogin.clear();
                //окно ввода логина и пароля закрывается
                Main.login.close();
                //открывается Main Stage
                stageMain.show();
            } else {
                  /* если авторизовавшийся пользователь - не руководитель, то открывается окно
                со списком задач Main_Stage_Executor_Controller*/
                Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("Main_Stage_Executor.fxml")));
                stageMain.setScene(mainScene);
                //поля очищаются, чтобы при повторном входе в сессии приходилось их заполнять заново
                fldPassword.clear();
                fldLogin.clear();
                //окно ввода логина и пароля закрывается
                Main.login.close();
                //открывается Main Stage
                stageMain.show();
            }
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

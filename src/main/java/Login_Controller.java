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

    private void connection(){
        try {
            boolean connect = Service_DB.testConnection();
            if (connect) {
                connectionStatus.setText("Подключено к серверу");
            }
        } catch (Exception e) {
            connectionStatus.setText("Подключение отсутствует");
        }
    }

    public void initialize() throws Exception {
        connection();
    }

    @FXML
    private void login() throws Exception {
        //метод, вызываемый по нажатию кнопки "Войти". Сейчас сравнивает правильность введённого логина и пароля с установленными непосредственно в методе
        //String rightLogin = "1";
        //String rightPassword = "1";
        //сравнивается введёное в fldLogin с локальной переменной rghtLogin
        boolean login = Service_DB.tryLogin(fldLogin.getText(), fldPassword.getText());
        // CurrentUser currentUser = new CurrentUser();
        if (login) {
            //если всё введено правильно, в currentUser передаётся имя пользователя из введённого в поле fldLogin
            //из базы данных получаем userId по переданному ранее userName и передаём его в CurrentUser
            //управление передаётся другому контроллеру, указанному в Main_Stage_Executor.fxml
            Current_User.setUserName(fldLogin.getText());
            Service_User_DB.setUserId(Current_User.getUserName());
            if (Service_User_DB.getUserPosition(Current_User.getUserId()).equals("Руководитель")) {
                /* если авторизовавшийся пользователь - руководитель, то открывается окно
                с редактированием задач Main_Stage_Manager*/
                Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("Main_Stage_Manager.fxml")));
                stageMain.setScene(mainScene);
                fldPassword.clear();
                fldLogin.clear();
                //окно ввода логина и пароля закрывается
                Main.login.close();
                //открывается Main Stage
                stageMain.show();
            }
            else {
                  /* если авторизовавшийся пользователь - не руководитель, то открывается окно
                с редактированием задач Main_Stage_Manager*/
                Scene mainScene = new Scene(FXMLLoader.load(getClass().getResource("Main_Stage_Executor.fxml")));
                stageMain.setScene(mainScene);
                fldPassword.clear();
                fldLogin.clear();
                //окно ввода логина и пароля закрывается
                Main.login.close();
                //открывается Main Stage
                stageMain.show();
            }
        } else {
            //если хотя-бы одно из полей введено неправильно срабатывает else, которое выводит в консоль сообщение
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

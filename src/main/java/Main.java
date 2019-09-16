import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Stage login;

    public static void main() {
        //этот класс обязателен, и обязательно с вызовом метода launch(), именно эта конструкция запускает приложение
        launch();
    }

    //приложение на JavaFX начинается с класса Main, наследующего класс Application от JavaFX
    @Override
    public void start(Stage stageLogin) throws IOException {
        //при запуске приложения через главный класс Main открывается окно входа Login.fxml и управление передаётся на его контроллер
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        login = stageLogin;
        login.setTitle("Новая задача");
        login.setResizable(false);
        //stageLogin.setMaxHeight(600);
        login.centerOnScreen();
        //stageLogin.setMaxWidth(800);
        login.setScene(scene);
        login.show();
    }

}
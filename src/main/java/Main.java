import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Stage stage;

    public static void main(String[] args) {
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
        stage = stageLogin;
        stage.setTitle("Новая задача");
        stage.setResizable(false);
        //stageLogin.setMaxHeight(600);
        stage.centerOnScreen();
        //stageLogin.setMaxWidth(800);
        stage.setScene(scene);
        stage.show();
    }

}
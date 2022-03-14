/*
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Stage login;
    FXMLLoader loader;
    Parent root;
    Scene scene;
    public static void main(String[] args) {
        // вызов унаследованного от Application метода launch() запускает JavaFX приложение
        launch();
    }

    @Override
    public void start(Stage stageLogin){
        //при запуске приложения через главный класс Main открывается окно входа Login.fxml и управление передаётся на его контроллер
        loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        try {
            root = loader.load();
        } catch (IOException e){
            System.err.println("Невозможно загрузить файл Login.fxml \n" + e.getMessage());
            System.exit(-1);
        }
        scene = new Scene(root);
        login = stageLogin;
        login.setResizable(false);
        login.centerOnScreen();
        login.setScene(scene);
        login.show();
    }

}*/

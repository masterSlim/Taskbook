package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import

import java.util.ArrayList;

public class Main extends Application {
    ArrayList taskList = new ArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Новая задача");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(360);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void saveTask(String taskName, String task){


    }


    public static void main(String[] args) {
        launch(args);
    }
}

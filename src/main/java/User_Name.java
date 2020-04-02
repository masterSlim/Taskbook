import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class User_Name extends Pane {
    @FXML
    private Label labelUserName;

    public User_Name() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User_Name.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    @FXML
    public void userInfo() throws IOException, SQLException {
        User_info_Controller controller = new User_info_Controller();
        controller.setUser(Service_User_DB.getUser(labelUserName.getText()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("User_Info.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String getText() {
        return labelUserName.getText();
    }

    public void setText(String userName) {
        labelUserName.setText(userName);
    }

}

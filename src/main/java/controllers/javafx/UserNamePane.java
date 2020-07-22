package controllers.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tbspring.models.ActiveUser;
import tbspring.models.User;
import tbspring.services.ServiceDBUser;

import java.io.IOException;
import java.sql.SQLException;

public class UserNamePane extends Pane {
    private ActiveUser activeUser;
    private User user;
    @FXML
    private Label labelUserName;

    public UserNamePane(ActiveUser activeUser, User user) throws IOException {
        this.activeUser = activeUser;
        this.user = user;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserNamePane.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        labelUserName.setText(user.getUserName());
    }

    @FXML
    public void userInfo() throws IOException, SQLException {
        UserInfoController controller = new UserInfoController(activeUser, user);
        controller.setUser(ServiceDBUser.getUser(labelUserName.getText()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserInfo.fxml"));
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

}

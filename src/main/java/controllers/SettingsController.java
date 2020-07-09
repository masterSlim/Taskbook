package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.ActiveUser;
import services.ServiceDBUser;

import java.sql.SQLException;
import java.util.Arrays;

public class SettingsController {
    ActiveUser activeUser;
    @FXML
    private Label labelUserName;
    @FXML
    private ImageView userpic;
    @FXML
    private TextArea areaDirectory;
    @FXML
    private Label labelPosition;
    @FXML
    private TextArea areaPhone;
    @FXML
    private TextArea areaEmail;
    @FXML
    private PasswordField areaNewPassword;
    @FXML
    private PasswordField areaOldPassword;
    @FXML
    private Button save;

    public SettingsController(ActiveUser activeUser) {
        this.activeUser = activeUser;
    }

    @FXML
    public void saveSettings(MouseEvent mouseEvent) throws SQLException {
        String directory = "";
        //сравнение и изменение директории
        System.out.println(areaDirectory.getText());
        System.out.println(activeUser.getDirectory());
        if ((activeUser.getDirectory() == null) || !(activeUser.getDirectory().equals(areaDirectory.getText()))) {
            activeUser.setDirectory(areaDirectory.getText());
            //для sql запроса нужно экранировать обратный слэш \
            directory = "directory = " + "\"" + activeUser.getDirectory().replace("\\", "\\\\") + "\"" + " ";
        }
        ServiceDBUser.executeUpdate("UPDATE users SET " + directory + "WHERE user_id = " + activeUser.getUserId() + ";");
        Stage close = (Stage) save.getScene().getWindow();
        close.close();
    }

    @FXML
    public void initialize() {
        labelUserName.setText(activeUser.getUserName());
        if (!activeUser.getUserPic().equals("null")) {
            userpic.setImage(new Image(activeUser.getUserPic()));
        } else {
            userpic.setImage(new Image("icons\\user.png"));
        }
        System.out.println(activeUser.getDirectory());
        labelPosition.setText(activeUser.getPosition().name());
        areaDirectory.setPromptText(activeUser.getDirectory());
        //TODO: неправильно отображает телефон
        areaPhone.setText(Arrays.toString(activeUser.getPhone()));
        areaEmail.setText(activeUser.getEmail());
    }
}

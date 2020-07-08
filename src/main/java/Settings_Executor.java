import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Current_User;
import services.Service_User_DB;

import java.sql.SQLException;

public class Settings_Executor {
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

    @FXML
    public void saveSettings(MouseEvent mouseEvent) throws SQLException {
        String directory = "";
        //сравнение и изменение директории
        System.out.println(areaDirectory.getText());
        System.out.println(Current_User.getDirectory());
        if((Current_User.getDirectory() == null) || !(Current_User.getDirectory().equals(areaDirectory.getText()))) {
            Current_User.setDirectory(areaDirectory.getText());
            //для sql запроса нужно экранировать обратный слэш \
            directory = "directory = " + "\"" + Current_User.getDirectory().replace("\\", "\\\\") +"\"" + " ";
        }
        Service_User_DB.executeUpdate("UPDATE users SET " + directory + "WHERE user_id = "+ Current_User.getUserId() + ";");
        Stage close = (Stage) save.getScene().getWindow();
        close.close();
    }
    @FXML
    public void initialize() {
        labelUserName.setText(Current_User.getUserName());
        if(!Current_User.getUserpick().equals("null")){
            userpic.setImage(new Image(Current_User.getUserpick()));
        } else{
            userpic.setImage(new Image("icons\\user.png"));
        }
        System.out.println(Current_User.getDirectory());
        labelPosition.setText(Current_User.getPosition());
        areaDirectory.setPromptText(Current_User.getDirectory());
        areaPhone.setText(Current_User.getPhone());
        areaEmail.setText(Current_User.getEmail());
    }
}

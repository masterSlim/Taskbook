import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

// класс является контроллером для NewTask.fxml
// создаю экземпляр класса Task
public class New_Task_Controller {
    @FXML
    byte priority = (byte) 0;
    int creatorId = 0; //нужно аменить вдальнейшем эти числа на получаемые из таблицы users
    int executorId = 1; //нужно заменить вдальнейшем эти числа на получаемые из таблицы users
    java.sql.Date createDate = java.sql.Date.valueOf(LocalDate.now());
    java.sql.Date startDate = java.sql.Date.valueOf(LocalDate.now());
    java.sql.Date deadline = java.sql.Date.valueOf(LocalDate.now());
    boolean isactive = true;

    @FXML
    private TextField titleField;
    @FXML
    private TextArea taskArea;
    @FXML
    private ImageView priorityButton;
    @FXML
    private ChoiceBox<String> executorIdBox;
    @FXML
    private DatePicker deadlineDatePicker;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private CheckBox activeCheckBox;


    // метод, меняющий приоритет задачи по нажатию кнопки
   /* public void changePriority() {
        if (getPriority() == (byte) 0) {

        }
    }*/

    //метод преобразовывающий введённые в тектстовые поля данные в одну цельную задачу

    @FXML
    public void initialize() throws SQLException {
        ObservableList<String> userNames = FXCollections.observableArrayList();
        for (int i = 1; i <= Service_User_DB.getAllUsers().size(); i++) {
            userNames.add(Service_User_DB.getAllUsers().get(i-1).getUserName());
        }
        executorIdBox.setItems(userNames);
    }

    @FXML
    private byte changePriority(MouseEvent mouseEvent) {
        if (priority == (byte) 0) {
            priority = (byte) 1;
            priorityButton.setImage(new Image("/icons/priority_high.png"));
            return priority;
        } else {
            priority = (byte) 0;
            priorityButton.setImage(new Image("/icons/priority_normal.png"));
            return priority;
        }
    }

    @FXML
    public void saveTask(MouseEvent mouseEvent) throws SQLException {
        //!!!нужно передать данные от полей сцены в нужные поля для ServiceDB который ниже!!!        ;
        if (deadlineDatePicker.getValue() != null) {
            deadline = Date.valueOf(deadlineDatePicker.getValue());
        }
        if (startDatePicker.getValue() != null) {
            startDate = Date.valueOf(startDatePicker.getValue());
        }

        isactive = activeCheckBox.isSelected();

        for (int i =1; i<=Service_User_DB.getAllUsers().size(); i++){
            if (executorIdBox.getValue().equals(Service_User_DB.getAllUsers().get(i-1).getUserName()))
            executorId = Service_User_DB.getAllUsers().get(i-1).getUserId();
        }

        Service_Task_DB.saveTasks(priority, creatorId, titleField.getText(), taskArea.getText(), executorId, createDate, startDate, deadline, isactive);
        //String.valueOf(LocalDateTime.now()), LocalDateTime.now(), LocalDateTime.now(), true);

        Main_Stage_Controller.stageNewTask.close();
    }


    //метод, отправляющий заполненную форму на сервер

    @FXML
    public void cancel(MouseEvent mouseEvent) {
        Main_Stage_Controller.stageNewTask.close();
    }

/*    public void saveTask(MouseEvent mouseEvent) {

        Main_Stage_Controller.stageNewTask.close();
    }*/
}


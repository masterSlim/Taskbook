import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// класс является контроллером для NewTask.fxml
// создаю экземпляр класса Task
public class New_Task_Controller {
    @FXML
    private byte priority = (byte) 0;
    private int creatorId; //нужно аменить вдальнейшем эти числа на получаемые из таблицы users
    private int executorId; //нужно заменить вдальнейшем эти числа на получаемые из таблицы users
    private Date createDate = java.sql.Date.valueOf(LocalDate.now());
    private Date startDate = java.sql.Date.valueOf(LocalDate.now());
    private Date deadline = java.sql.Date.valueOf(LocalDate.now());
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
    private Hyperlink laterButton;
    @FXML
    private TilePane fileTilePane;
    private List<String[]> filesPaths;

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
            userNames.add(Service_User_DB.getAllUsers().get(i - 1).getUserName());
        }
        executorIdBox.setItems(userNames);
        fileTilePane.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.ANY);
            }
            event.consume();
        });
        fileTilePane.setOnDragDropped(dragEvent -> {
            List<File> files = dragEvent.getDragboard().getFiles();
            filesPaths = new ArrayList<>(files.size());
            for (int i = 1; i <= files.size(); i++) {
                String[] temp = {files.get(i - 1).getName(), files.get(i - 1).getPath()};
                filesPaths.add(i - 1, temp);
            }
        });
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
    public void saveTask(MouseEvent mouseEvent) throws SQLException, IOException {
        //!!!нужно передать данные от полей сцены в нужные поля для ServiceDB который ниже!!!        ;
        if (deadlineDatePicker.getValue() != null) {
            deadline = Date.valueOf(deadlineDatePicker.getValue());
        }

        if (startDatePicker.getValue() != null) {
            startDate = Date.valueOf(startDatePicker.getValue());
        }

        for (int i = 1; i <= Service_User_DB.getAllUsers().size(); i++) {
            //поиск и присваивание id исполнителя перебором по таблице users
            try {
                if (executorIdBox.getValue().equals(Service_User_DB.getAllUsers().get(i - 1).getUserName())) {
                    executorId = Service_User_DB.getAllUsers().get(i - 1).getUserId();
                }
            } catch (Exception e) {
                executorId = 0;
            }
        }
        creatorId = Current_User.getUserId();
        if (filesPaths != null) {
            //здесь происходит копирование файлов в папку пользователя, если они приложены к задаче
            for (int i = 1; i <= filesPaths.size(); i++) {
                String newFileDirectory = "C:\\Users\\Mr\\Desktop\\destination\\" + filesPaths.get(i - 1)[0];
                Path originalFilePath = Path.of(filesPaths.get(i - 1)[1]);
                Path newFilePath = Paths.get(newFileDirectory);
                Files.copy(originalFilePath, newFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        Service_Task_DB.saveTasks(priority, creatorId, titleField.getText(), taskArea.getText(), executorId, createDate, startDate, deadline, true);
        //String.valueOf(LocalDateTime.now()), LocalDateTime.now(), LocalDateTime.now(), true);

        Main_Stage_Executor_Controller.stageNewTask.close();
    }


    //метод, отправляющий заполненную форму на сервер

    @FXML
    public void cancel(MouseEvent mouseEvent) {
        Main_Stage_Executor_Controller.stageNewTask.close();
    }

    public void draft(MouseEvent mouseEvent) throws SQLException {
        if (deadlineDatePicker.getValue() != null) {
            deadline = Date.valueOf(deadlineDatePicker.getValue());
        }
        if (startDatePicker.getValue() != null) {
            startDate = Date.valueOf(startDatePicker.getValue());
        }

        for (int i = 1; i <= Service_User_DB.getAllUsers().size(); i++) {
            if (executorIdBox.getValue().equals(Service_User_DB.getAllUsers().get(i - 1).getUserName()))
                executorId = Service_User_DB.getAllUsers().get(i - 1).getUserId();
        }

        Service_Task_DB.saveTasks(priority, creatorId, titleField.getText(), taskArea.getText(), executorId, createDate, startDate, deadline, false);
        //String.valueOf(LocalDateTime.now()), LocalDateTime.now(), LocalDateTime.now(), true);

        Main_Stage_Executor_Controller.stageNewTask.close();
    }

    public void addFile(MouseDragEvent mouseDragEvent) {
    }
}

/*    public void saveTask(MouseEvent mouseEvent) {

        Main_Stage_Controller.stageNewTask.close();
    }*/


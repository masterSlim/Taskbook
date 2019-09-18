import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Current_Task_Manager_Controller {
    static int selected;
    @FXML
    private Label taskTitle;
    @FXML
    private Text task;
    @FXML
    private Label deadline;
    @FXML
    private ImageView priorityImageView;
    @FXML
    private Button closeTask;

    public void initialize() throws SQLException {
        PreparedStatement currentTaskPs = Service_DB.getConnection().prepareStatement("SELECT * FROM tasks where task_id =?;");
        currentTaskPs.setInt(1, selected);
        ResultSet currentTaskRs = currentTaskPs.executeQuery();
        currentTaskRs.next();
        try {
            taskTitle.setText(currentTaskRs.getString("title"));
            task.setText(currentTaskRs.getString("task"));
            deadline.setText(currentTaskRs.getDate("deadline").toString());
            if (currentTaskRs.getByte("priority") == (byte) 1) {
                priorityImageView.setImage(new Image("/icons/priority_high.png"));
                taskTitle.setTextFill(Color.RED);
            }
            if (currentTaskRs.getBoolean("is_closed")){
                closeTask.setDisable(true);
                closeTask.setText("Задача закрыта");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Service_DB.getConnection().close();
        }
    }

    public void closeTask(MouseEvent mouseEvent) throws SQLException {
        PreparedStatement currentTaskPs = Service_DB.getConnection().prepareStatement("UPDATE tasks SET is_active=?, is_closed = ? WHERE task_id=?");
        currentTaskPs.setBoolean(1, false);
        currentTaskPs.setBoolean(2,true);
        currentTaskPs.setInt(3, selected);
        currentTaskPs.executeUpdate();
        closeTask.setDisable(true);
        closeTask.setText("Задача закрыта");
    }
}

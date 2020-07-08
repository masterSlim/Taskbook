package models;

import java.util.Date;

public class Task_Lite extends Task {
        public void setTaskLite(int taskId, String title, Date deadline) {
        setTaskId(taskId);
        setTitle(title);
        setDeadline(deadline);
    }
}

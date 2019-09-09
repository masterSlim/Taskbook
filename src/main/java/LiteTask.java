import java.util.Date;

public class LiteTask extends Task {
    public void liteTask(int taskId, String title, Date deadline) {
        setTaskId (taskId);
        setTitle(title);
        setDeadline(deadline);
    }
}

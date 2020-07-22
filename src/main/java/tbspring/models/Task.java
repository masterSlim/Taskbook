package tbspring.models;

import java.util.Date;

public class Task {

    private int taskId;
    private Byte priority;
    private int creatorId;
    private String title;
    private String task;
    private int executorId;
    private String createDateTime;
    private String closeDateTime;
    private Date deadline;
    private Boolean active;

    public Task(int taskId, String title, Date deadline) {
        setTaskId(taskId);
        setTitle(title);
        setDeadline(deadline);
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getExecutorId() {
        return executorId;
    }

    public void setExecutorId(int executorId) {
        this.executorId = executorId;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getCloseDateTime() {
        return closeDateTime;
    }

    public void setCloseDateTime(String closeDateTime) {
        this.closeDateTime = closeDateTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }


}

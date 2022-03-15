package tbspring.models;


import tbspring.entities.TaskEntity;

import java.util.Date;

public class Task {
    private long taskId;
    private Byte priority;
    private long creatorId;
    private String title;
    private String task;
    private long executorId;
    private String createDateTime;
    private String closeDateTime;
    private Date deadline;
    private Boolean active;

    public Task(long taskId, String title, Date deadline) {
        setTaskId(taskId);
        setTitle(title);
        setDeadline(deadline);
    }

    public static Task toModel(TaskEntity taskEntity) {
        return new Task(taskEntity.getTaskId(), taskEntity.getTitle(),
                taskEntity.getDeadline());
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(long executorId) {
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

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
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

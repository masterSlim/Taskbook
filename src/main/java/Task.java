import java.util.Date;

abstract class Task {

    private static int taskId;
    private static Byte priority;
    private static int creatorId;
    private static String title;
    private static String task;
    private static int executorId;
    private static String createDateTime;
    private static String closeDateTime;
    private static Date deadline;
    private static Boolean active;

    public static int getTaskId() {
        return taskId;
    }

    public static void setTaskId(int taskId) {
        Task.taskId = taskId;
    }

    public static Byte getPriority() {
        return priority;
    }

    public static void setPriority(Byte priority) {
        Task.priority = priority;
    }

    public static int getCreatorId() {
        return creatorId;
    }

    public static void setCreatorId(int creatorId) {
        Task.creatorId = creatorId;
    }

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        Task.title = title;
    }

    public static String getTask() {
        return task;
    }

    public static void setTask(String task) {
        Task.task = task;
    }

    public static int getExecutorId() {
        return executorId;
    }

    public static void setExecutorId(int executorId) {
        Task.executorId = executorId;
    }

    public static String getCreateDateTime() {
        return createDateTime;
    }

    public static void setCreateDateTime(String createDateTime) {
        Task.createDateTime = createDateTime;
    }

    public static String getCloseDateTime() {
        return closeDateTime;
    }

    public static void setCloseDateTime(String closeDateTime) {
        Task.closeDateTime = closeDateTime;
    }

    public static Date getDeadline() {
        return deadline;
    }

    public static void setDeadline(Date deadline) {
        Task.deadline = deadline;
    }

    public static Boolean getActive() {
        return active;
    }

    public static void setActive(Boolean active) {
        Task.active = active;
    }


}

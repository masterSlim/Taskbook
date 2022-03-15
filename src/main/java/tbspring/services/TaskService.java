package tbspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbspring.entities.TaskEntity;
import tbspring.repository.TaskRepo;

import java.util.Collection;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepository;

    public TaskEntity getTask(long id) {
        TaskEntity task = taskRepository.findById(id).get();
        if (task == null) {
            throw new NullPointerException("Пользователь не найден");
        } else return task;
    }

    public Collection<TaskEntity> getAllTasksForUser(long userId) {
        return taskRepository.getAllByExecutorId(userId);
    }

    public TaskEntity saveTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    public void deleteTask(TaskEntity task) {
        taskRepository.delete(task);
    }

}

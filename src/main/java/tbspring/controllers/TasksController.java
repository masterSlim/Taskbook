package tbspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbspring.entities.TaskEntity;
import tbspring.models.Task;
import tbspring.services.TaskService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class TasksController {
    @Autowired
    TaskService taskService;

    @GetMapping("/task")
    public ResponseEntity<Task> showUser(@RequestParam long id) {
        try {
            return ResponseEntity.ok(Task.toModel(taskService.getTask(id)));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<Collection<Task>> showAllTasksForUser(@RequestParam long userId, Principal principal ) {
        Collection<Task> response = new ArrayList<>();
        for (TaskEntity e : taskService.getAllTasksForUser(userId)) {
            response.add(Task.toModel(e));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/task")
    public ResponseEntity<?> saveUser(@RequestBody TaskEntity taskEntity) {
        try {
            return ResponseEntity.ok(taskService.saveTask(taskEntity));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/task")
    public ResponseEntity<?> deleteUser(@RequestParam long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package dan.ToDoWebApp.controllers;


import dan.ToDoWebApp.model.Task;
import dan.ToDoWebApp.service.TaskService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return new ResponseEntity<>(taskService.AllTasks(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable ObjectId id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }
}

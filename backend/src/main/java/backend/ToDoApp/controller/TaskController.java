package backend.ToDoApp.controller;

import backend.ToDoApp.excepton.TaskNotFoundException;
import backend.ToDoApp.model.Task;
import backend.ToDoApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5000")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Integer id) throws TaskNotFoundException {
        return ResponseEntity.ok(taskService.get(id));
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        taskService.save(task);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Integer id, @RequestBody Task updatedTask) throws TaskNotFoundException {
        Task task = taskService.get(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDuedate(updatedTask.getDuedate());
        task.setPriority(updatedTask.getPriority());
        task.setCategory(updatedTask.getCategory());
        taskService.updateTask(id, task);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Integer id) throws TaskNotFoundException {
        Task task = taskService.get(id);
        taskService.delete(id);
        return ResponseEntity.ok(task);
    }
}

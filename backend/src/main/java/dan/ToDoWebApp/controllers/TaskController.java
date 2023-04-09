package dan.ToDoWebApp.controllers;
import dan.ToDoWebApp.model.Category;
import dan.ToDoWebApp.model.Task;
import dan.ToDoWebApp.service.CategoryService;
import dan.ToDoWebApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5000")
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskService.AllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") String id) {
        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/{id}/dueDate")
    public ResponseEntity<Task> getTaskByDueDate(@PathVariable("id") String id) {
        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        taskService.saveTask(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") String id, @RequestBody Task task) {
        taskService.updateTask(id, task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") String id) {
        taskService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package dan.ToDoWebApp.controllers;


import dan.ToDoWebApp.model.Category;
import dan.ToDoWebApp.model.Task;
import dan.ToDoWebApp.repository.TaskRepository;
import dan.ToDoWebApp.service.CategoryService;
import dan.ToDoWebApp.service.TaskService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5000")
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryService categoryService;

    @GetMapping("dueDate/{id}")
    public ResponseEntity<?> getDueDate(@PathVariable ObjectId id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = taskOptional.get();
        Date dueDate = task.getDueDate();

        return ResponseEntity.ok(dueDate);
    }
    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@RequestParam(required = false) String sortBy) {
        List<Task> tasks;
        if (sortBy != null) {
            tasks = switch (sortBy) {
                case "priority" -> taskRepository.findByOrderByPriorityAsc();
                case "dueDate" -> taskRepository.findByOrderByDueDateAsc();
                default -> taskRepository.findAll();
            };
        } else {
            tasks = taskRepository.findAll();
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable ObjectId id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable ObjectId id, @RequestBody Task task) {
        taskService.UpdateTask(id, task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable ObjectId id) {
        taskRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<Task> completeTask(@PathVariable ObjectId id) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isPresent()) {
            task.get().setCompleted(true);
            taskService.UpdateTask(id, task.get());
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

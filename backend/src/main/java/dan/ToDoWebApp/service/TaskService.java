package dan.ToDoWebApp.service;

import dan.ToDoWebApp.model.Task;
import dan.ToDoWebApp.repository.TaskRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public List<Task> AllTasks() {
        return taskRepository.findAll();
    }
    public Optional<Task> getTaskById(ObjectId id) {
        return taskRepository.findById(id);
    }
}

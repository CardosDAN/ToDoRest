package dan.ToDoWebApp.service;

import dan.ToDoWebApp.model.Task;
import dan.ToDoWebApp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    public List<Task> AllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void saveTask(Task task) {

        taskRepository.save(task);
    }
    public void updateTask(String id, Task task) {
        Task taskToUpdate = taskRepository.findById(id).orElse(null);
        assert taskToUpdate != null;
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setDueDate(task.getDueDate());
        taskToUpdate.setPriority(task.getPriority());
        taskToUpdate.setCompleted(task.getCompleted());
        taskRepository.save(taskToUpdate);
    }


    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }
}

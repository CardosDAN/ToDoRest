package backend.ToDoApp.service;

import backend.ToDoApp.excepton.TaskNotFoundException;
import backend.ToDoApp.model.Task;
import backend.ToDoApp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> listAll() {
        return (List<Task>) taskRepository.findAll();
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public Task get(Integer id) throws TaskNotFoundException {
        Optional<Task> result = taskRepository.findById(id);
        System.out.println(result);
        if (result.isPresent()) {
            return result.get();
        }
        throw new TaskNotFoundException("Could not found any task with the id: " + id);
    }


    public void updateTask(Integer id, Task task) throws TaskNotFoundException {
        Long count = taskRepository.countById(id);
        if (count == null || count == 0) {
            throw new TaskNotFoundException("Could not found any task with the id: " + id);
        }
        task.setId(id);
        taskRepository.save(task);
    }

    public void delete(Integer id) throws TaskNotFoundException {
        Long count = taskRepository.countById(id);
        if (count == null || count == 0) {
            throw new TaskNotFoundException("Could not found any task with the id: " + id);
        }
        taskRepository.deleteById(id);
    }
}

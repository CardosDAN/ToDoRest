package backend.ToDoApp.repository;

import backend.ToDoApp.model.Category;
import backend.ToDoApp.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends CrudRepository<Task, Integer> {
    Long countById(Integer id);
}

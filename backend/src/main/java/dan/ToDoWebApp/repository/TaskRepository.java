package dan.ToDoWebApp.repository;

import dan.ToDoWebApp.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

}

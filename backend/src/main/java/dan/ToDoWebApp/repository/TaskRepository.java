package dan.ToDoWebApp.repository;

import dan.ToDoWebApp.model.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, ObjectId> {

    List<Task> findByOrderByPriorityAsc();
    List<Task> findByOrderByDueDateAsc();

}

package dan.ToDoWebApp.repository;

import dan.ToDoWebApp.model.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends MongoRepository<Category, ObjectId> {

}

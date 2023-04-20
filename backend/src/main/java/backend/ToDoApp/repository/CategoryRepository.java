package backend.ToDoApp.repository;

import backend.ToDoApp.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Long countById(Integer id);
}

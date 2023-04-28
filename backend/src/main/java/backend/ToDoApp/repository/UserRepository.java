package backend.ToDoApp.repository;

import backend.ToDoApp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Long countById(Integer id);
    User findByEmail(String email);
}

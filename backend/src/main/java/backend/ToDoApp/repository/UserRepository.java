package backend.ToDoApp.repository;

import backend.ToDoApp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Long countById(Integer id);
    User findByUsername(String username);
}

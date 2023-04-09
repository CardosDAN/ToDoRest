package dan.ToDoWebApp.service;

import dan.ToDoWebApp.model.Category;
import dan.ToDoWebApp.model.Task;
import dan.ToDoWebApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private MongoTemplate mongoTemplate;


    public Category createCategory(String name, String id) {
        Category category = categoryRepository.insert(new Category(name));

        mongoTemplate.update(Task.class)
                .matching(org.springframework.data.mongodb.core.query.Query.query(org.springframework.data.mongodb.core.query.Criteria.where("_id").is(id)))
                .apply(org.springframework.data.mongodb.core.query.Update.update("categoryId", category))
                .first();
        return category;
    }

    public java.util.List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }

    public void updateCategory(String id, String name) {
        Category category = categoryRepository.findById(id).get();
        category.setName(name);
        categoryRepository.save(category);
    }

    public Category getCategory(String id) {
        return categoryRepository.findById(id).get();
    }
}

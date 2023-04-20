package backend.ToDoApp.controller;

import backend.ToDoApp.excepton.CategoryNotFoundException;
import backend.ToDoApp.model.Category;
import backend.ToDoApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5000")
public class CategoryController {

    @Autowired private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.listAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Category> getCategoryById(Integer id) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws CategoryNotFoundException {
        categoryService.updateCategory(category.getId(), category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping
    public ResponseEntity<Category> deleteCategory(@RequestBody Category category) throws CategoryNotFoundException {
        categoryService.delete(category.getId());
        return ResponseEntity.ok(category);
    }
}

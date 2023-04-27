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

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer id) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.get(id));
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        categoryService.save(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category updatedCategory) throws CategoryNotFoundException {
        Category category = categoryService.get(id);
        category.setName(updatedCategory.getName());
        categoryService.updateCategory(id, category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Integer id) throws CategoryNotFoundException {
        Category category = categoryService.get(id);
        categoryService.delete(id);
        return ResponseEntity.ok(category);
    }
}

package dan.ToDoWebApp.controllers;

import dan.ToDoWebApp.model.Category;
import dan.ToDoWebApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5000")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody Map<String, String> payload) {
        String id = payload.get("id");
        Category category = categoryService.createCategory(payload.get("name"), id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category added with ID: " + category.getId());
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted with ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable String id, @RequestBody Map<String, String> payload) {
        categoryService.updateCategory(id, payload.get("name"));
        return ResponseEntity.status(HttpStatus.OK).body("Category updated with ID: " + id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(id));
    }

}


package dan.ToDoWebApp.controllers;

import dan.ToDoWebApp.model.Category;
import dan.ToDoWebApp.service.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody Map<String, String> payload) {
        ObjectId id = new ObjectId(payload.get("_id"));
        Category category = categoryService.createCategory(payload.get("name"), id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category added with ID: " + category.getId());
    }

}


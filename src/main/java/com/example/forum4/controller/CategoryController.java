package com.example.forum4.controller;

import com.example.forum4.entity.Category;
import com.example.forum4.entity.Post;
import com.example.forum4.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

@GetMapping("/all")
public List<Category> findAll() {
    List<Category> categories = categoryService.findAll();

    // 检查数据
    for (Category category : categories) {
        if (category.getCategoryId() == null) {
            System.err.println("Invalid category ID: " + category.getCategoryId());
        }
    }

    return categories;
}
    //@GetMapping("/all")
public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = categoryService.findAll();
    return ResponseEntity.ok(categories);
}
    @GetMapping("/{categoryId}/posts")
    public ResponseEntity<List<Post>> getPostsByCategoryId(@PathVariable Long categoryId) {
        List<Post> posts = categoryService.findPostsByCategoryId(categoryId);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }


    // Other CRUD operations...
}

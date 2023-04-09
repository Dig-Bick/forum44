package com.example.forum4.controller;

import com.example.forum4.entity.Category;
import com.example.forum4.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }
    @GetMapping("/all")
public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = categoryService.findAll();
    return ResponseEntity.ok(categories);
}


    // Other CRUD operations...
}

package com.example.forum4.service;

import com.example.forum4.mapper.CategoryMapper;
import com.example.forum4.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    // Other CRUD operations...
}

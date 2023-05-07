package com.example.forum4.service;

import com.example.forum4.entity.Post;
import com.example.forum4.mapper.CategoryMapper;
import com.example.forum4.entity.Category;
import com.example.forum4.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private PostMapper postMapper;

    public List<Category> findAll() {
        return categoryMapper.findAll();
    }
    public List<Post> findPostsByCategoryId(Long categoryId) {
        return postMapper.findByCategoryId(categoryId);
    }
    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }


    // Other CRUD operations...
}

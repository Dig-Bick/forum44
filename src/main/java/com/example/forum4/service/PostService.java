package com.example.forum4.service;

import com.example.forum4.entity.Post;
import com.example.forum4.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    public List<Post> findAll() {
        return postMapper.findAll();
    }

    // Other CRUD operations...
}

package com.example.forum4.controller;

import com.example.forum4.entity.Post;
import com.example.forum4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> findAll() {
        return postService.findAll();
    }
    @GetMapping("/recommend")
public ResponseEntity<List<Post>> getRecommendedPosts(@RequestParam("userId") int userId) {
    List<Post> recommendedPosts = postService.getRecommendedPosts(userId);
    return ResponseEntity.ok(recommendedPosts);
}

    // Other CRUD operations...
}

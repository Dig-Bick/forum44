package com.example.forum4.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum4.entity.Post;
import com.example.forum4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postManagement")
public class PostManagementController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public IPage<Post> getPosts(@RequestParam(defaultValue = "1") Integer currentPage,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(required = false) Long categoryId) {
        Page<Post> page = new Page<>(currentPage, pageSize);
        return postService.page(page, categoryId);
    }

    @PutMapping("/posts/{postId}")
    public boolean updatePost(@PathVariable Integer postId, @RequestBody Post post) {
        post.setPostId(postId);
        return postService.updateById(post);
    }

    @DeleteMapping("/posts/{postId}")
    public boolean deletePost(@PathVariable Integer postId) {
        return postService.removeById(postId);
    }
}

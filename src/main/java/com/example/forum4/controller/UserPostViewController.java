package com.example.forum4.controller;

import com.example.forum4.entity.UserPostView;
import com.example.forum4.service.UserPostViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_post_views")
public class UserPostViewController {

    @Autowired
    private UserPostViewService userPostViewService;

    @PostMapping
    public int create(@RequestBody UserPostView userPostView) {
        return userPostViewService.create(userPostView);
    }

    @GetMapping("/{userId}")
    public List<UserPostView> getViewsByUserId(@PathVariable Long userId) {
        return userPostViewService.getViewsByUserId(userId);
    }

}

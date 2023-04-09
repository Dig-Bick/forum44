package com.example.forum4.controller;

import com.example.forum4.entity.User;
import com.example.forum4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());

        if (existingUser == null) {
            userService.create(user);
            return ResponseEntity.ok("注册成功");
        } else {
            return ResponseEntity.badRequest().body("用户名已存在");
        }
    }
}

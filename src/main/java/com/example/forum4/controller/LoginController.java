package com.example.forum4.controller;

import com.example.forum4.entity.User;
import com.example.forum4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        Map<String, String> result = userService.login(user.getUsername(), user.getPassword());
        if (result != null && !result.isEmpty()) {
            String token = result.get("token");

            // 创建一个新的 Map 并将 token 放入
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "用户名或密码错误"));
        }
    }
}

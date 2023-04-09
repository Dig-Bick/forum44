package com.example.forum4.controller;

import com.example.forum4.entity.User;
import com.example.forum4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/find/{username}")
    public User findByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/list")
    public Page<User> findAll(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam(value = "search", required = false) String search) {
        List<User> users = userService.findAll(search);
        long total = users.size();
        int startIndex = Math.min(page * size, users.size());
        int endIndex = Math.min(startIndex + size, users.size());
        return new PageImpl<>(users.subList(startIndex, endIndex), PageRequest.of(page, size), total);
    }

    @PutMapping("/update/{id}")
    public User update(@PathVariable("id") Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        return userService.login(username, password);
    }
}
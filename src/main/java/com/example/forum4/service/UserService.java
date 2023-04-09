package com.example.forum4.service;

import com.example.forum4.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User create(User user);

    User findByUsername(String username);

    List<User> findAll(String search);

    User update(Integer id, User user);

    void delete(Integer id);

    Map<String, String> login(String username, String password);

    String generateToken(User user);
}

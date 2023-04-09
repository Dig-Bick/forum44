package com.example.forum4.service.impl;

import com.example.forum4.entity.User;
import com.example.forum4.mapper.UserMapper;
import com.example.forum4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public User create(User user) {
        user.setPassword(md5(user.getPassword()));
        userMapper.insert(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<User> findAll(String search) {
        List<User> users = userMapper.selectAll();
        if (search != null && !search.trim().isEmpty()) {
            users = users.stream()
                    .filter(user -> user.getUsername().contains(search))
                    .collect(Collectors.toList());
        }
        return users;
    }

    @Override
    public User update(Integer id, User user) {
        User existingUser = userMapper.selectById(id);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(md5(user.getPassword()));
            userMapper.update(existingUser);
        }
        return existingUser;
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public Map<String, String> login(String username, String password) {
        User user = findByUsername(username);
        if (user != null && md5(password).equals(user.getPassword())) {
            String token = generateToken(user);
            redisTemplate.opsForValue().set(token, user, 30, TimeUnit.MINUTES);

            Map<String, String> responseData = new HashMap<>();
            responseData.put("token", token);

            return responseData;
        }
        return null;
    }

    @Override
    public String generateToken(User user) {
        return UUID.randomUUID().toString();
    }

    private String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 加密错误", e);
        }
    }
}

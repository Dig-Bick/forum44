package com.example.forum4.service;

import com.example.forum4.entity.UserPostView;
import com.example.forum4.mapper.UserPostViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPostViewService {

    @Autowired
    private UserPostViewMapper userPostViewMapper;

    public int create(UserPostView userPostView) {
        return userPostViewMapper.insert(userPostView);
    }

    public List<UserPostView> getViewsByUserId(Long userId) {
        return userPostViewMapper.selectByUserId(userId);
    }

}

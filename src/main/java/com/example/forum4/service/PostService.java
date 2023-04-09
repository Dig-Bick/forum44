package com.example.forum4.service;

import com.example.forum4.entity.Post;
import com.example.forum4.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    public List<Post> findAll() {
        return postMapper.findAll();
    }
    public List<Post> getRecommendedPosts(int userId) {
        // 此处添加实现贴子推荐的逻辑，例如根据用户浏览历史进行推荐
        // 这里仅返回最新的10篇帖子作为示例
        return postMapper.findAll().stream().limit(10).collect(Collectors.toList());
    }

    // Other CRUD operations...
}

package com.example.forum4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.forum4.entity.Post;

import java.util.List;

public interface PostService extends IService<Post> {
    List<Post> findAll();
    List<Post> getRecommendedPosts(int userId);
    List<Post> getPostsByCategoryId(Long categoryId);
}

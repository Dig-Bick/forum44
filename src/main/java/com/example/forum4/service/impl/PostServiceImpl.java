package com.example.forum4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.forum4.entity.Post;
import com.example.forum4.mapper.PostMapper;
import com.example.forum4.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {
    @Override
    public List<Post> findAll() {
        return getBaseMapper().selectList(null);
    }

    @Override
    public List<Post> getRecommendedPosts(int userId) {
        // 这里添加根据userId获取推荐帖子的逻辑
        // 示例：
        return getBaseMapper().selectList(null);
    }

    @Override
    public List<Post> getPostsByCategoryId(Long categoryId) {
        // 这里添加根据categoryId获取帖子的逻辑
        // 示例：
        return getBaseMapper().selectList(null);
    }
}

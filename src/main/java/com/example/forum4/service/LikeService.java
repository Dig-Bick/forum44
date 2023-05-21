package com.example.forum4.service;

import com.example.forum4.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeMapper likeMapper;

    // 点赞
    public boolean addLike(Integer postId, Integer userId) {
        return likeMapper.insertLike(postId, userId) > 0;
    }

    // 取消点赞
    public boolean removeLike(Integer postId, Integer userId) {
        return likeMapper.deleteLike(postId, userId) > 0;
    }

    // 获取点赞数量
    public Integer getLikeCount(Integer postId) {
        return likeMapper.countLikesByPostId(postId);
    }
    public boolean isLikedByUser(Integer postId, Integer userId) {
        Integer count = likeMapper.findLikeByUserIdAndPostId(postId, userId);
        return count != null && count > 0;
    }

    public boolean isCategoryLikedByUser(Integer categoryId, Integer userId) {
        Integer count = likeMapper.findLikeByCategoryAndUserId(categoryId, userId);
        return count != null && count > 0;
    }

}


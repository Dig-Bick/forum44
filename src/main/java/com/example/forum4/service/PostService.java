package com.example.forum4.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum4.entity.Comment;
import com.example.forum4.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();
    List<Post> getRecommendedPosts(int userId);
    List<Post> getPostsByCategoryId(Long categoryId);
    IPage<Post> page(Page<Post> page,Long categoryId);
    boolean updateById(Post post);
    boolean removeById(Integer postId);
    List<Post> getRandomPosts(int count);
    Optional<Post> findById(Long id);
    Comment createCommentReply(Long postId, Long commentId, Comment comment, Integer userId);
    List<Post> getPostsByUserId(Integer userId);
    void updateViewCount(Long id);
    List<Post> getTopPostsByCategoryId(Integer categoryId, int limit);
    void saveUserLike(Integer userId, Long categoryId);


    int save(Post post);
}

package com.example.forum4.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum4.entity.Comment;
import com.example.forum4.entity.Post;
import com.example.forum4.mapper.PostMapper;
import com.example.forum4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> findAll() {
        return postMapper.selectList(null);
    }

    @Override
    public List<Post> getRecommendedPosts(int userId) {
        // 这里添加根据userId获取推荐帖子的逻辑
        // 示例：
        return postMapper.selectList(null);
    }

    @Override
    public List<Post> getPostsByCategoryId(Long categoryId) {
        // 这里添加根据categoryId获取帖子的逻辑
        // 示例：
        return postMapper.selectByCategoryId(categoryId);
    }

    @Override
    public IPage<Post> page(Page<Post> page,Long categoryId) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        // 添加查询条件：筛选 category_id 为指定值的记录
            if (categoryId != null) {
            queryWrapper.eq("category_id", categoryId);
        }
        return postMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean updateById(Post post) {
        return postMapper.updateById(post) > 0;
    }

    @Override
    public boolean removeById(Integer postId) {
        return postMapper.deleteById(postId) > 0;
    }
     @Override
    public List<Post> getRandomPosts(int count) {
        List<Post> allPosts = findAll();
        Collections.shuffle(allPosts);
        return allPosts.stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public Optional<Post> findById(Long id) {
    return postMapper.findById(id);
}

    @Override
    public int save(Post post) {
        return postMapper.save(post);
    }

    @Override
    public Comment createCommentReply(Long postId, Long commentId, Comment comment, Integer userId) {
        comment.setPostId(postId.intValue());
        comment.setUserId(userId);

        comment.setParentCommentId(commentId.intValue());
        comment.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        postMapper.insertCommentReply(comment);
        return comment;
    }

    @Override
    public List<Post> getPostsByUserId(Integer userId) {
        return postMapper.selectPostsByUserId(userId);
    }
    @Override
    public void updateViewCount(Long id) {
        postMapper.updateViewCount(id);
    }



}

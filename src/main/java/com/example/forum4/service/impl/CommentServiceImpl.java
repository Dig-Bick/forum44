// CommentServiceImpl.java
package com.example.forum4.service.impl;

import com.example.forum4.entity.Comment;
import com.example.forum4.mapper.CommentMapper;
import com.example.forum4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void createComment(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Integer postId) {
        return commentMapper.selectCommentsByPostId(postId);
    }
}
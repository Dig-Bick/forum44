// CommentService.java
package com.example.forum4.service;

import com.example.forum4.entity.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Comment comment);
    List<Comment> getCommentsByPostId(Integer postId);
}

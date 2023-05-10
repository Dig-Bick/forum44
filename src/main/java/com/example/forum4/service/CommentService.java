// CommentService.java
package com.example.forum4.service;

import com.example.forum4.entity.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Comment comment);
    List<Comment> getCommentsByPostId(Integer postId);
    List<Comment> getRepliesByParentCommentId(Integer parentCommentId);
    void fetchReplies(Comment comment);
    void deleteComment(Long commentId);
    Long findUserIdByCommentId(Long commentId) ;
    Long findUserIdByPostId(Long postId);

}

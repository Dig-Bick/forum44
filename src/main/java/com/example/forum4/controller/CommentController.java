package com.example.forum4.controller;

import com.example.forum4.entity.Comment;
import com.example.forum4.entity.User;
import com.example.forum4.service.CommentService;
import com.example.forum4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody Comment comment) {
        comment.setCreatedAt(new Date());
        commentService.createComment(comment);
        return ResponseEntity.ok("Comment created");
    }


    @GetMapping("/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable String postId) {
        Integer postIdInt;
        try {
            postIdInt = Integer.parseInt(postId);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
        List<Comment> comments = commentService.getCommentsByPostId(postIdInt);
        for (Comment comment : comments) {
            User user = userService.findById(comment.getUserId());
            if (user != null) {
                comment.setUsername(user.getUsername());
            }
        }
        return ResponseEntity.ok(comments);
    }

}
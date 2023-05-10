package com.example.forum4.controller;

import com.example.forum4.config.RabbitMQConfig;
import com.example.forum4.entity.Comment;
import com.example.forum4.entity.Notification;
import com.example.forum4.entity.User;
import com.example.forum4.service.CommentService;
import com.example.forum4.service.PostService;
import com.example.forum4.service.UserService;
import com.example.forum4.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private PostService postService;

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody Comment comment) {
        comment.setCreatedAt(new Date());
        commentService.createComment(comment);

        Long recipientUserId;
        if (comment.getParentCommentId() != null) {
            recipientUserId = commentService.findUserIdByCommentId(Long.valueOf(comment.getParentCommentId()));
        } else {
            recipientUserId = commentService.findUserIdByPostId(Long.valueOf(comment.getPostId()));
        }

        if (recipientUserId != null) {
            String message = "您有一条新评论: " + comment.getContent();
            Notification notification = new Notification(recipientUserId.intValue(), message);
            rabbitTemplate.convertAndSend(RabbitMQConfig.NOTIFICATION_QUEUE, notification);
        }

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
        comment.setReplies(getCommentReplies(comment));
    }
    return ResponseEntity.ok(comments);
}



    @PostMapping("/posts/{postId}/comments/{commentId}/replies")
    public ResponseEntity<Comment> createReply(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody Comment comment, @RequestHeader("Authorization") String token) {
        System.out.println("Create reply request received for postId: " + postId + ", commentId: " + commentId);
        String jwt = token.substring(7);
        Claims claims = JwtUtil.parseJWT(jwt);
        System.out.println("Parsed claims: " + claims.toString());
        User user = userService.findById(JwtUtil.parseJWT(jwt).get("userId", Integer.class));

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Comment result = postService.createCommentReply(postId, commentId, comment, user.getId());
        return ResponseEntity.ok().body(result);
    }
    private List<Comment> getCommentReplies(Comment comment) {
        List<Comment> replies = commentService.getRepliesByParentCommentId(comment.getCommentId());
        for (Comment reply : replies) {
            User user = userService.findById(reply.getUserId());
            if (user != null) {
                reply.setUsername(user.getUsername());
            }
            reply.setReplies(getCommentReplies(reply));
        }
        return replies;
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
      commentService.deleteComment(commentId);
      return ResponseEntity.noContent().build();
    }




}

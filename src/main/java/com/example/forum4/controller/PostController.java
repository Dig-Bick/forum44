package com.example.forum4.controller;

import com.example.forum4.entity.Post;
import com.example.forum4.entity.UserPostView;
import com.example.forum4.service.PostService;
import com.example.forum4.service.UserPostViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserPostViewService userPostViewService;

    @GetMapping("/all")
    public List<Post> findAll() {
        return postService.findAll();
    }
    @GetMapping("/{id}")
public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
    Optional<Post> optionalPost = postService.findById(id);
    if (optionalPost.isPresent()) {
        return ResponseEntity.ok(optionalPost.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

    @GetMapping("/recommended")
    public ResponseEntity<List<Post>> getRecommendedPosts(@RequestParam("userId") Long userId) {
        // 获取用户浏览过的帖子
        List<UserPostView> views = userPostViewService.getViewsByUserId(userId);

        // 根据浏览过的帖子获取推荐帖子，这里只是一个简单示例，您可以根据实际需求进行修改
        List<Post> recommendedPosts = new ArrayList<>();

        if (views.isEmpty()) {
            // 如果用户没有浏览过任何帖子，则返回随机推荐的帖子
            return ResponseEntity.ok(postService.getRandomPosts(1)); // 返回 1 个随机推荐的帖子
        } else {
            for (UserPostView view : views) {
                Integer categoryId = view.getPost().getCategoryId();
                // 将 Integer 类型的 categoryId 转换为 Long 类型
                Long longCategoryId = categoryId != null ? categoryId.longValue() : null;
                recommendedPosts.addAll(postService.getPostsByCategoryId(longCategoryId));
            }
        }
        return ResponseEntity.ok(recommendedPosts);
    }

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody Post post) {
        int result = postService.save(post);
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED).body(post);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Other CRUD operations...
}

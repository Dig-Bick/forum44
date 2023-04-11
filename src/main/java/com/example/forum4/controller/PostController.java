package com.example.forum4.controller;

import com.example.forum4.entity.Post;
import com.example.forum4.entity.UserPostView;
import com.example.forum4.service.PostService;
import com.example.forum4.service.UserPostViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserPostViewService userPostViewService;

    @GetMapping("/all")
    public List<Post> findAll() {
        return postService.findAll();//Cannot resolve method 'findAll' in 'PostService'
    }
    @GetMapping("/recommend")
    public ResponseEntity<List<Post>> getRecommendedPosts(@RequestParam("userId") int userId) {
        List<Post> recommendedPosts = postService.getRecommendedPosts(userId);//Cannot resolve method 'getRecommendedPosts' in 'PostService'
        return ResponseEntity.ok(recommendedPosts);
    }

    @GetMapping("/recommended/{userId}")
    public List<Post> getRecommendedPosts(@PathVariable Long userId) {
        // 获取用户浏览过的帖子
        List<UserPostView> views = userPostViewService.getViewsByUserId(userId);
        // 根据浏览过的帖子获取推荐帖子，这里只是一个简单示例，您可以根据实际需求进行修改
        List<Post> recommendedPosts = new ArrayList<>();
        for (UserPostView view : views) {
            Integer categoryId = view.getPost().getCategoryId();
            // 将 Integer 类型的 categoryId 转换为 Long 类型
            Long longCategoryId = categoryId != null ? categoryId.longValue() : null;
            recommendedPosts.addAll(postService.getPostsByCategoryId(longCategoryId));//Cannot resolve method 'getPostsByCategoryId' in 'PostService'
        }
        return recommendedPosts;
    }

    // Other CRUD operations...
}

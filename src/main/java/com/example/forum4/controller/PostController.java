package com.example.forum4.controller;

import com.example.forum4.entity.IndexedPost;
import com.example.forum4.entity.Post;
import com.example.forum4.entity.UserPostView;
import com.example.forum4.service.LikeService;
import com.example.forum4.service.PostSearchRepository;
import com.example.forum4.service.PostService;
import com.example.forum4.service.UserPostViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserPostViewService userPostViewService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private PostSearchRepository postSearchRepository;

    @GetMapping("/all")
    public List<Post> findAll() {
        return postService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id, @RequestParam("userId") Integer userId) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            // Update the view count of the post
            postService.updateViewCount(id);

            // Update the user post view
            UserPostView userPostView = new UserPostView();
            userPostView.setUserId(userId);
            userPostView.setPostId(id.intValue());
            userPostView.setViewTime(Timestamp.valueOf(LocalDateTime.now()));
            userPostViewService.addUserPostView(userPostView);

            Integer likeCount = likeService.getLikeCount(post.getPostId()); // 获取贴子的赞数
            post.setLikeCount(likeCount); // 将赞数设置到返回的 Post 对象中
            return ResponseEntity.ok(post);
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
            return ResponseEntity.ok(postService.getRandomPosts(5)); // 返回 1 个随机推荐的帖子
        } else {
            for (UserPostView view : views) {
                Integer categoryId = view.getPost().getCategoryId();
                // 将 Integer 类型的 categoryId 转换为 Long 类型
                Long longCategoryId = categoryId != null ? categoryId.longValue() : null;
                recommendedPosts.addAll(postService.getPostsByCategoryId(longCategoryId));
            }
        }
        System.out.println(recommendedPosts);
        return ResponseEntity.ok(recommendedPosts);
    }

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody Post post) {
        int result = postService.save(post);
        if (result > 0) {
            // 将 Post 转换为 IndexedPost，然后保存到 Elasticsearch
            IndexedPost indexedPost = new IndexedPost();
            indexedPost.setPostId(post.getPostId());
            indexedPost.setUserId(post.getUserId());
            indexedPost.setTitle(post.getTitle());
            indexedPost.setContent(post.getContent());
            indexedPost.setCategoryId(post.getCategoryId());
            indexedPost.setCreatedAt(post.getCreatedAt());
            indexedPost.setUpdatedAt(post.getUpdatedAt());
            indexedPost.setViewCount(post.getViewCount());
            indexedPost.setLikeCount(post.getLikeCount());
            postSearchRepository.save(indexedPost);

            return ResponseEntity.status(HttpStatus.CREATED).body(post);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // 点赞
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> addLike(@PathVariable("postId") Integer postId, @RequestParam("userId") String userIdStr) {
        Integer userId = Integer.parseInt(userIdStr); // 将字符串类型的 userId 转换为整数类型
        if (likeService.addLike(postId, userId)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("点赞成功");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("点赞失败");
        }
    }

    // 取消点赞
    @DeleteMapping("/{postId}/like")
    public ResponseEntity<String> removeLike(@PathVariable("postId") Integer postId, @RequestParam("userId") String userIdStr) {
        Integer userId = Integer.parseInt(userIdStr); // 将字符串类型的 userId 转换为整数类型
        if (likeService.removeLike(postId, userId)) {
            return ResponseEntity.status(HttpStatus.OK).body("取消点赞成功");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("取消点赞失败");
        }
    }

    @GetMapping("/{postId}/like-status")
    public ResponseEntity<Map<String, Object>> getLikeStatusAndCount(@PathVariable("postId") Integer postId, @RequestParam("userId") String userIdStr) {
        Integer userId = Integer.parseInt(userIdStr);
        Integer likeCount = likeService.getLikeCount(postId);
        Boolean isLiked = likeService.isLikedByUser(postId, userId);
        Map<String, Object> response = new HashMap<>();
        response.put("likeCount", likeCount);
        response.put("isLiked", isLiked);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Integer userId) {
        List<Post> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        try {
            postService.removeById(postId.intValue());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<IndexedPost>> searchPosts(@RequestParam String query) {
        System.out.println(query);
        Page<IndexedPost> posts = postSearchRepository.findByContentOrTitle(query,query, Pageable.unpaged());
        System.out.println(posts.getContent());
        return new ResponseEntity<>(posts.getContent(), HttpStatus.OK);
    }




    // Other CRUD operations...
}

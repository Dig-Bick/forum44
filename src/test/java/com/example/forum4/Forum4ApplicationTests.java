package com.example.forum4;

import com.example.forum4.entity.IndexedPost;
import com.example.forum4.entity.Post;
import com.example.forum4.mapper.UserMapper;
import com.example.forum4.service.PostSearchRepository;
import com.example.forum4.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Forum4ApplicationTests {

    @Resource
    private PostService postService;

    @Resource
    private PostSearchRepository postSearchRepository;

    @Test
    void contextLoads() {
        indexAllPosts();
    }

    private void indexAllPosts() {
        List<Post> posts = postService.findAll();
        for (Post post : posts) {
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
        }
    }
}

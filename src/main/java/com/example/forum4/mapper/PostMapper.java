package com.example.forum4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum4.entity.Comment;
import com.example.forum4.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper extends BaseMapper<Post> {


    int deleteByPrimaryKey(Long id);

    int insert(Post record);


    Post selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Post record);

    @Select("SELECT * FROM posts")
    @Results({
        @Result(column = "post_id", property = "postId"),
        @Result(column = "user_id", property = "userId"),
        @Result(column = "title", property = "title"),
        @Result(column = "content", property = "content"),
        @Result(column = "category_id", property = "categoryId"),
        @Result(column = "created_at", property = "createdAt"),
        @Result(column = "updated_at", property = "updatedAt"),
        @Result(column = "view_count", property = "viewCount")
    })
    List<Post> findAll();


    List<Post> selectByCategoryId(Long categoryId);

    @Select("SELECT * FROM posts WHERE category_id = #{categoryId}")
    @Results({
        @Result(column = "post_id", property = "postId"),
        @Result(column = "user_id", property = "userId"),
        @Result(column = "title", property = "title"),
        @Result(column = "content", property = "content"),
        @Result(column = "category_id", property = "categoryId"),
        @Result(column = "created_at", property = "createdAt"),
        @Result(column = "updated_at", property = "updatedAt"),
        @Result(column = "view_count", property = "viewCount")
    })
    List<Post> findByCategoryId(Long categoryId);
    @Select("SELECT * FROM posts WHERE post_id = #{id}")
    Optional<Post> findById(Long id);

    @Insert("INSERT INTO posts (user_id, title, content, category_id, created_at, updated_at, view_count) VALUES (#{userId}, #{title}, #{content}, #{categoryId}, #{createdAt}, #{updatedAt}, #{viewCount})")
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    int save(Post post);

    @Insert("INSERT INTO comments (post_id, user_id, parent_comment_id, content, created_at) VALUES (#{postId}, #{userId}, #{parentCommentId}, #{content}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "commentId", keyColumn = "comment_id")
    int insertCommentReply(Comment comment);

    List<Post> selectPostsByUserId(Integer userId);

    @Select("SELECT user_id FROM posts WHERE post_id = #{postId}")
    Long findUserIdByPostId(Long postId);

   @Update("UPDATE posts SET view_count = view_count + 1 WHERE post_id = #{id}")
    void updateViewCount(Long id);

    @Select("SELECT * FROM posts WHERE category_id = #{categoryId} ORDER BY view_count DESC LIMIT #{limit}")
    @Results({
        @Result(column = "post_id", property = "postId"),
        @Result(column = "user_id", property = "userId"),
        @Result(column = "title", property = "title"),
        @Result(column = "content", property = "content"),
        @Result(column = "category_id", property = "categoryId"),
        @Result(column = "created_at", property = "createdAt"),
        @Result(column = "updated_at", property = "updatedAt"),
        @Result(column = "view_count", property = "viewCount")
    })
    List<Post> selectTopPostsByCategoryId(@Param("categoryId") Integer categoryId, @Param("limit") int limit);

    @Insert("INSERT INTO user_category_likes (user_id, category_id) VALUES (#{userId}, #{categoryId})")
    void insertUserLike(Integer userId, Long categoryId);


}

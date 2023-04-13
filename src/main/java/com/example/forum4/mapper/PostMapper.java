package com.example.forum4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum4.entity.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

}

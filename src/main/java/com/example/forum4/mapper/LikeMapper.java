package com.example.forum4.mapper;

import com.example.forum4.entity.Like;
import org.apache.ibatis.annotations.*;

/**
* @author aaaa
* @description 针对表【likes】的数据库操作Mapper
* @createDate 2023-05-08 14:46:16
* @Entity com.example.forum4.entity.Like
*/
@Mapper
public interface LikeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Like record);

    int insertSelective(Like record);

    Like selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);
        // 添加点赞
    @Insert("INSERT INTO likes (post_id, user_id, created_at) VALUES (#{postId}, #{userId}, NOW())")
    int insertLike(@Param("postId") Integer postId, @Param("userId") Integer userId);

    // 删除点赞
    @Delete("DELETE FROM likes WHERE post_id = #{postId} AND user_id = #{userId}")
    int deleteLike(@Param("postId") Integer postId, @Param("userId") Integer userId);

    // 获取点赞数量
    @Select("SELECT COUNT(*) FROM likes WHERE post_id = #{postId}")
    int countLikesByPostId(Integer postId);

    Integer findLikeByUserIdAndPostId(@Param("postId") Integer postId, @Param("userId") Integer userId);

    @Select("SELECT COUNT(*) FROM user_category_likes WHERE category_id = #{categoryId} AND user_id = #{userId}")
    Integer findLikeByCategoryAndUserId(@Param("categoryId") Integer categoryId, @Param("userId") Integer userId);

}

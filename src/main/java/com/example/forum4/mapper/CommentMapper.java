package com.example.forum4.mapper;

import com.example.forum4.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author aaaa
* @description 针对表【comments】的数据库操作Mapper
* @createDate 2023-05-08 18:06:13
* @Entity com.example.forum4.entity.Comment
*/
@Mapper
public interface CommentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectCommentsByPostId(Integer postId);

    @Select("SELECT * FROM comments WHERE parent_comment_id = #{parentCommentId}")
    List<Comment> findByParentCommentId(Integer parentCommentId);

    Long findUserIdByCommentId(Long commentId);


}

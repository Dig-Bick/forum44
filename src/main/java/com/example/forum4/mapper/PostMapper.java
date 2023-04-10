package com.example.forum4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum4.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author aaaa
* @description 针对表【posts】的数据库操作Mapper
* @createDate 2023-04-09 16:29:22
* @Entity com.example.forum4.entity.Post
*/
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    int deleteByPrimaryKey(Long id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
    @Select("SELECT * FROM posts")
    List<Post> findAll();

    List<Post> selectByCategoryId(Long categoryId);

}

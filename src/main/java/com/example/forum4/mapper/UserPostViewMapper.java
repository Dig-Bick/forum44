package com.example.forum4.mapper;

import com.example.forum4.entity.UserPostView;

/**
* @author aaaa
* @description 针对表【user_post_views】的数据库操作Mapper
* @createDate 2023-04-09 21:32:27
* @Entity com.example.forum4.entity.UserPostView
*/
public interface UserPostViewMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserPostView record);

    int insertSelective(UserPostView record);

    UserPostView selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPostView record);

    int updateByPrimaryKey(UserPostView record);

}

package com.example.forum4.mapper;

import com.example.forum4.entity.UserPostView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPostViewMapper {

    int insert(UserPostView userPostView);

    List<UserPostView> selectByUserId(@Param("userId") Long userId);



}

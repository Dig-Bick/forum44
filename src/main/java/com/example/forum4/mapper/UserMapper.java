package com.example.forum4.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.forum4.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(String username);

    // User selectOne(QueryWrapper<User> wrapper);

    // int selectCount(Object o);

    @Select("SELECT * FROM user")
    List<User> selectAll();

    @Update("UPDATE user SET username = #{username}, password = #{password} WHERE id = #{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteById(Integer id);

     @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(Integer id);

    @Select("SELECT * FROM user LIMIT #{offset}, #{size}")
    List<User> findAll(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM user")
    long count();
}

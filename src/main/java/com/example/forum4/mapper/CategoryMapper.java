package com.example.forum4.mapper;

import com.example.forum4.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    // 添加 findAll 方法
    @Select("SELECT * FROM categories")
    List<Category> findAll();

        // 添加 findById 方法
    @Select("SELECT * FROM categories WHERE category_id = #{id}")
    Category findById(Long id);
}

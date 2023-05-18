package com.example.forum4.mapper;

import com.example.forum4.entity.Statistic;
import org.apache.ibatis.annotations.Mapper;

/**
* @author aaaa
* @description 针对表【statistics】的数据库操作Mapper
* @createDate 2023-05-18 19:28:59
* @Entity com.example.forum4.entity.Statistic
*/
@Mapper
public interface StatisticMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Statistic record);

    int insertSelective(Statistic record);

    Statistic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Statistic record);

    int updateByPrimaryKey(Statistic record);

     int getTotalUsers();
     int getTotalPosts();
     int getActiveUsers();
     int getTotalPageViews();

}

package com.example.forum4.service;

import com.example.forum4.mapper.StatisticMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class StatisticService {

    @Autowired
    StatisticMapper statisticMapper;

    public Map<String, Integer> getStatistics() {
        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("totalUsers", statisticMapper.getTotalUsers());
        statistics.put("totalPosts", statisticMapper.getTotalPosts());
        statistics.put("activeUsers", statisticMapper.getActiveUsers());
        statistics.put("totalPageViews", statisticMapper.getTotalPageViews());
        return statistics;
    }
}

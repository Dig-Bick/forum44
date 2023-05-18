package com.example.forum4.controller;

import com.example.forum4.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
@Controller
public class StatisticController {

    @Autowired
    StatisticService statisticsService;

    @GetMapping("/api/statistics")
    public ResponseEntity<Map<String, Integer>> getStatistics() {

        Map<String, Integer> statistics = statisticsService.getStatistics();
        return ResponseEntity.ok(statistics);
    }
}

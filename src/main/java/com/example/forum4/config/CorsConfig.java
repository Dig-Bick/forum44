package com.example.forum4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 允许所有路径
                        .allowedOriginPatterns("*") // 使用 allowedOriginPatterns 替代 allowedOrigins，允许所有来源
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的 HTTP 方法
                        .allowedHeaders("*") // 允许所有请求头
                        .exposedHeaders("Authorization") // 允许暴露 Authorization 头
                        .allowCredentials(true) // 允许携带凭据（如 cookie 等）
                        .maxAge(3600); // 预检请求的有效期
            }
        };
    }
}

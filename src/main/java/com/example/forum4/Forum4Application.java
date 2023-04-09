package com.example.forum4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.forum4.mapper")
public class Forum4Application {

    public static void main(String[] args) {
        SpringApplication.run(Forum4Application.class, args);
    }

}

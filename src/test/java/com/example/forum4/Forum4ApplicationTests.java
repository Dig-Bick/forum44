package com.example.forum4;

import com.example.forum4.entity.User;
import com.example.forum4.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Forum4ApplicationTests {

@Resource
private UserMapper userMapper;
    @Test
    void contextLoads() {
        //System.out.println(userMapper.insert(new User(1,"1","2","2","2","2")));
    }

}

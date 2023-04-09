package com.example.forum4.service.impl;

import com.example.forum4.entity.Admin;
import com.example.forum4.mapper.AdminMapper;
import com.example.forum4.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminByName(String adminName) {
        return adminMapper.selectByAdminName(adminName);
    }

    @Override
    public void save(Admin admin) {
        adminMapper.insertSelective(admin);
    }

}

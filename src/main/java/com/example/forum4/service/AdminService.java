package com.example.forum4.service;

import com.example.forum4.entity.Admin;

public interface AdminService {
    Admin findAdminByName(String adminName);
    void save(Admin admin);
}

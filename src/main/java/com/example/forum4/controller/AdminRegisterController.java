package com.example.forum4.controller;

import com.example.forum4.entity.Admin;
import com.example.forum4.service.AdminService;
import com.example.forum4.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/admin")
public class AdminRegisterController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin admin) {
        Admin storedAdmin = adminService.findAdminByName(admin.getAdminName());

        if (storedAdmin == null) {
            String encryptedPassword = MD5Util.encode(admin.getPassword());
            admin.setPassword(encryptedPassword);
            admin.setLastLogin(new Date()); // 设置 lastLogin 为当前时间
            adminService.save(admin);
            return ResponseEntity.ok("Registration successful");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Admin already exists");
    }
}

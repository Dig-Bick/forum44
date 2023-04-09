package com.example.forum4.controller;

import com.example.forum4.entity.Admin;
import com.example.forum4.service.AdminService;
import com.example.forum4.util.MD5Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Admin admin) {
        Map<String, Object> result = new HashMap<>();
        Admin storedAdmin = adminService.findAdminByName(admin.getAdminName());

        if (storedAdmin != null) {
            String encryptedPassword = MD5Util.encode(admin.getPassword());
            if (storedAdmin.getPassword().equals(encryptedPassword)) {
                // 在这里生成和返回 JWT token
                String token = generateJWTToken(storedAdmin);
                result.put("status", "success");
                result.put("token", token);
            } else {
                result.put("status", "error");
                result.put("message", "Incorrect password");
            }
        } else {
            result.put("status", "error");
            result.put("message", "Admin not found");
        }

        return result;
    }

    private String generateJWTToken(Admin admin) {
        final long EXPIRATION_TIME = 864_000_000; // 10 days
        final String SECRET_KEY = "your_secret_key_here";
        return Jwts.builder()
                .setSubject(admin.getAdminName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}

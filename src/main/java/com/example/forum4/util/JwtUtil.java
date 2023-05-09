package com.example.forum4.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "yourSecretKey";

    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(jwt)
            .getBody();
    }

    public static String createJWT(Integer id) {
        return Jwts.builder()
            .claim("userId", id)
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }
}

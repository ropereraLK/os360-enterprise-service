package com.os360.enterprise.service;

import com.os360.enterprise.entity.User;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final String SECRET_KEY = "your-secret-key"; // store securely!

    public String generateToken(User user) {
//        long expiration = 3600_000; // 1 hour
//        return Jwts.builder()
//                .setSubject(user.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();

        return "Hi";
    }
}

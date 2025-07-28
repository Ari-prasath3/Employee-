package com.example.EmpApp.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY = "myverysecretkey12345678901234567890";
    private final long ACCESS_EXPIRATION = 1000 * 60 * 15;
    private final long REFRESH_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateAccessToken(String email, Long empId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("empId", empId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    public Long extractEmpId(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody().get("empId", Long.class);
    }
}
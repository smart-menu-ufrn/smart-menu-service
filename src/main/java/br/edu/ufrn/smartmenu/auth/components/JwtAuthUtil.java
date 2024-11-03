package br.edu.ufrn.smartmenu.auth.components;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtAuthUtil {
    
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private Long expirationTime; // Hours

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Long currentTimeMillis = System.currentTimeMillis();

        Date issuedAt = new Date(currentTimeMillis);
        Date expiration = new Date(
            currentTimeMillis + (1000 * 60 * 60 * this.expirationTime)
        );

        String token = Jwts.builder()
            .claims(claims)
            .subject(subject)
            .issuedAt(issuedAt)
            .expiration(expiration)
            .signWith(this.getSecretKey())
            .compact();
        
        return token;
    }

    private Claims extractAllClaims(String token) {
        Claims claims = Jwts.parser()
            .verifyWith(this.getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();

        return claims;
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();

        String token = this.createToken(claims, email);

        return token;
    }

    public Boolean isValidToken(String token) {
        Date nowDate = new Date();

        Claims claims = this.extractAllClaims(token);

        Boolean isTokenIssued = claims.getIssuedAt().before(nowDate);

        Boolean isTokenExpired = claims.getExpiration().before(nowDate);

        Boolean isValid = (
            isTokenIssued && !isTokenExpired
        );

        return isValid;
    }

    public String retrieveEmailFromToken(String token) {
        Claims claims = this.extractAllClaims(token);

        String email = claims.getSubject();

        return email;
    }

}

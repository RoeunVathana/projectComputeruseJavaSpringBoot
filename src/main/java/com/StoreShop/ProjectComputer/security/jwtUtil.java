package com.StoreShop.ProjectComputer.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class jwtUtil {

    private static final String DEFAULT_SECRET =
            "change-this-development-secret-key-to-a-long-random-value-123456";

    @Value("${app.jwt.secret:" + DEFAULT_SECRET + "}")
    private String secret;

    @Value("${app.jwt.access-expiration-ms:600000}")
    private long accessTokenExpiration;

    @Value("${app.jwt.refresh-expiration-ms:604800000}")
    private long refreshTokenExpiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessJwtToken(String username, List<String> roles, List<String> permission) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles == null ? List.of() : roles)
                .claim("permissions", permission == null ? List.of() : permission)
                .claim("token_type", "access")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateRefreshJwtToken(String username) {
        return Jwts.builder()
                .subject(username)
                .claim("token_type", "refresh")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    public Claims parseAccessToken(String token) {
        Claims claims = parse(token);
        if (!"access".equals(claims.get("token_type", String.class))) {
            throw new JwtException("Token is not an access token");
        }
        return claims;
    }

    public Claims parseRefreshToken(String token) {
        Claims claims = parse(token);
        if (!"refresh".equals(claims.get("token_type", String.class))) {
            throw new JwtException("Token is not a refresh token");
        }
        return claims;
    }

    private Claims parse(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isAccessTokenValid(String token) {
        try {
            parseAccessToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}

package com.rikkei.hackathon.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {
    private final String secretKey;

    public JwtService(
            @Value("${app.security.jwt.secret:rikkei_secret_key_super_secure_do_not_share_123456}") String secretKey
    ) {
        this.secretKey = secretKey;
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(toSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    private SecretKey toSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}

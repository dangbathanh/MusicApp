package com.backend.MusicApp.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtTokenParser {

    private final Key key;

    public JwtTokenParser(Key key) {
        this.key = key;
    }

    public JwtClaims parse(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new JwtClaims(
                Long.valueOf(claims.getSubject()),
                claims.get("role", String.class),
                claims.getIssuedAt().toInstant(),
                claims.getExpiration().toInstant()
        );
    }
}
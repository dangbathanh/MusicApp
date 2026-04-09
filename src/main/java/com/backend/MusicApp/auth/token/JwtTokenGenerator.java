package com.backend.MusicApp.auth.token;

import com.backend.MusicApp.auth.time.TimeProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtTokenGenerator implements TokenGenerator{
    private static final long ACCESS_TOKEN_MINUTES = 15;
    private static final long REFRESH_TOKEN_DAYS = 30;
    private final Key key;
    private final TimeProvider timeProvider;

    public JwtTokenGenerator(TimeProvider timeProvider, Key key) {
        this.timeProvider = timeProvider;
        this.key = key;
    }

    @Override
    public String generateAccessToken(Long userId, String role) {
        Instant now = timeProvider.now();
        Instant expiry = now.plus(ACCESS_TOKEN_MINUTES, ChronoUnit.MINUTES);

        return Jwts.builder()
                .subject(userId.toString())
                .claim("role", role)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(key)
                .compact();
    }

    @Override
    public String generateRefreshToken(Long userId) {
        Instant now = timeProvider.now();
        Instant expiry = now.plus(REFRESH_TOKEN_DAYS, ChronoUnit.DAYS);

        return Jwts.builder()
                .subject(userId.toString())           // sub
                .claim("type", "refresh")                // phân biệt token
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(key)
                .compact();
    }
}

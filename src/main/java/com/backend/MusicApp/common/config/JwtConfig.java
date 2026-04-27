package com.backend.MusicApp.common.config;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
@Getter
public class JwtConfig {
    @Value("${JWT_SECRET_KEY}")
    private String secretKey;
    @Value("${JWT_ACCESS_TOKEN_EXPIRATION_MINUTES}")
    private long accessTokenMinutes;
    @Value("${JWT_REFRESH_TOKEN_EXPIRATION_DAYS}")
    private long refreshTokenDays;
    @Bean
    public Key jwtSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
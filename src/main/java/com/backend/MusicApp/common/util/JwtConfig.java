package com.backend.MusicApp.common.util;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class JwtConfig {

    @Bean
    public Key jwtSigningKey() {
        // Sử dụng cùng một chuỗi secret key
        String secretString = "z7G9xP2vR5sB8uE1hK4mN7qT0wW3zZ6iC9vB2nM5xX8zZ1aS4dF7gJ0kL3pQ6r9";
        return Keys.hmacShaKeyFor(secretString.getBytes());
    }
}
package com.backend.MusicApp.auth.token;

import io.jsonwebtoken.Claims;

import java.time.Instant;
import java.util.UUID;

public interface TokenGenerator {
    String generateAccessToken(Long userId, String role, Instant now);
    String generateRefreshToken(Long userId, UUID jti, Instant now);
    Claims parseRefreshToken(String refreshToken);
}

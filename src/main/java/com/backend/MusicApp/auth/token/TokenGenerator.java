package com.backend.MusicApp.auth.token;

public interface TokenGenerator {
    String generateAccessToken(Long userId, String role);
    String generateRefreshToken(Long userId);
}

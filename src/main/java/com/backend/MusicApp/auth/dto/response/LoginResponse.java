package com.backend.MusicApp.auth.dto.response;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        String username,
        String email
) {}

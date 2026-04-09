package com.backend.MusicApp.auth.dto.result;

public record LoginResult(
        String accessToken,
        String refreshToken,
        String username,
        String email
) {}
package com.backend.MusicApp.auth.dto.response;

public record RefreshResponse(
        String accessToken,
        String refreshToken
) {}
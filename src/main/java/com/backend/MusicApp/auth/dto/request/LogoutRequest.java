package com.backend.MusicApp.auth.dto.request;

public record LogoutRequest(
        String refreshToken,
        String deviceId
) {}

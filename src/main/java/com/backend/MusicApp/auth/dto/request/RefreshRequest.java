package com.backend.MusicApp.auth.dto.request;

public record RefreshRequest(
        String refreshToken,
        String deviceId
) {}
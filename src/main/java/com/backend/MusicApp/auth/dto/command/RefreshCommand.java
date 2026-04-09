package com.backend.MusicApp.auth.dto.command;

public record RefreshCommand(
        String refreshToken,
        String deviceId
) {}

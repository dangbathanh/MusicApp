package com.backend.MusicApp.auth.dto.command;

public record LogoutCommand(
        String refreshToken,
        String deviceId
) {}
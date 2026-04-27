package com.backend.MusicApp.user.api.dto;

public record NewUserCommand(
        String email,
        String username,
        String passwordHash
) {}

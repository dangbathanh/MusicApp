package com.backend.MusicApp.auth.dto.result;

public record RegisterResult(
        Long userId,
        String email
) {}

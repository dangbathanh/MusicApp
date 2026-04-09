package com.backend.MusicApp.auth.dto.result;

public record TokenResult(
        String accessToken,
        String refreshToken
) {}

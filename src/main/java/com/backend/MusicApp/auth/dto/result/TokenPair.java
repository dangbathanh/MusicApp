package com.backend.MusicApp.auth.dto.result;

import java.time.Instant;

public record TokenPair(
        String accessToken,
        String refreshToken,
        Instant refreshTokenExpiresAt
) {}
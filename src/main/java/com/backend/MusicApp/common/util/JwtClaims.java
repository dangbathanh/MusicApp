package com.backend.MusicApp.common.util;

import java.time.Instant;

public record JwtClaims(
        Long userId,
        String role,
        Instant issuedAt,
        Instant expiresAt
) {}

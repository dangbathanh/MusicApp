package com.backend.MusicApp.common.util;

import java.util.UUID;

public record JwtClaims(
        Long userId,
        String role,
        UUID jti
) {}

package com.backend.MusicApp.user.api.dto;

import com.backend.MusicApp.user.enums.UserRole;
import com.backend.MusicApp.user.enums.UserStatus;

import java.time.Instant;

public record UserAccount(
        Long id,
        String email,
        String username,
        UserRole role,
        UserStatus status,
        Instant createdAt
) {}

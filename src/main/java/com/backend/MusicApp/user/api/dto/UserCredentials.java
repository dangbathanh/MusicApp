package com.backend.MusicApp.user.api.dto;

import com.backend.MusicApp.user.enums.UserRole;
import com.backend.MusicApp.user.enums.UserStatus;

public record UserCredentials(
        Long id,
        String email,
        UserRole role,
        UserStatus status,
        String passwordHash
) {}

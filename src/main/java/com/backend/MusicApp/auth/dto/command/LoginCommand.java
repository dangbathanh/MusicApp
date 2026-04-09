package com.backend.MusicApp.auth.dto.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginCommand(
        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 8, max = 64)
        String password,
        String deviceId,
        String deviceName,
        String ipAddress
) {}
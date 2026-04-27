package com.backend.MusicApp.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @Email
        @Schema(example = "user@gmail.com", description = "Email đăng nhập")
        String email,

        @Schema(example = "123456", description = "Mật khẩu")
        String password,

        String deviceId,
        String deviceName
) {
}

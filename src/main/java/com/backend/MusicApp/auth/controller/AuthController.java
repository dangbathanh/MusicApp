package com.backend.MusicApp.auth.controller;

import com.backend.MusicApp.auth.dto.command.LoginCommand;
import com.backend.MusicApp.auth.dto.command.LogoutCommand;
import com.backend.MusicApp.auth.dto.command.RefreshCommand;
import com.backend.MusicApp.auth.dto.command.RegisterCommand;
import com.backend.MusicApp.auth.dto.request.LoginRequest;
import com.backend.MusicApp.auth.dto.request.LogoutRequest;
import com.backend.MusicApp.auth.dto.request.RefreshRequest;
import com.backend.MusicApp.auth.dto.request.RegisterRequest;
import com.backend.MusicApp.auth.dto.response.LoginResponse;
import com.backend.MusicApp.auth.dto.response.LogoutResponse;
import com.backend.MusicApp.auth.dto.response.RefreshResponse;
import com.backend.MusicApp.auth.dto.response.RegisterResponse;
import com.backend.MusicApp.auth.dto.result.LoginResult;
import com.backend.MusicApp.auth.dto.result.RegisterResult;
import com.backend.MusicApp.auth.dto.result.TokenResult;
import com.backend.MusicApp.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request,
            HttpServletRequest httpRequest
    ) {
        LoginCommand command = new LoginCommand(
                request.email(),
                request.password(),
                request.deviceId(),
                request.deviceName(),
                httpRequest.getRemoteAddr()
        );

        LoginResult result = authService.login(command);

        return ResponseEntity.ok(
                new LoginResponse(
                        result.accessToken(),
                        result.refreshToken(),
                        result.username(),
                        result.email()
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request
    ) {
        RegisterCommand command = new RegisterCommand(
                request.username(),
                request.email(),
                request.password()
        );

        RegisterResult result = authService.register(command);

        return ResponseEntity.ok(
                new RegisterResponse(
                        "Đăng ký tài khoản thành công!",
                        "SUCCESS"
                )
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(
            @RequestBody RefreshRequest request
    ) {
        RefreshCommand command = new RefreshCommand(
                request.refreshToken(),
                request.deviceId()
        );

        TokenResult result = authService.refresh(command);

        return ResponseEntity.ok(
                new RefreshResponse(
                        result.accessToken(),
                        result.refreshToken()
                )
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(
            @RequestBody LogoutRequest request
    ) {
        LogoutCommand command = new LogoutCommand(
                request.refreshToken(),
                request.deviceId()
        );

        authService.logout(command);

        return ResponseEntity.ok(
                new LogoutResponse("Logged out successfully")
        );
    }

}

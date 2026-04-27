package com.backend.MusicApp.auth.controller;

import com.backend.MusicApp.auth.dto.request.LoginRequest;
import com.backend.MusicApp.auth.dto.request.LogoutRequest;
import com.backend.MusicApp.auth.dto.request.RefreshRequest;
import com.backend.MusicApp.auth.dto.request.RegisterRequest;
import com.backend.MusicApp.auth.dto.response.LoginResponse;
import com.backend.MusicApp.auth.dto.response.RefreshResponse;
import com.backend.MusicApp.auth.internal.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Đăng ký, Đăng nhập, Logout, Refresh token rotation")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Đăng nhập người dùng", description = "Trả về Access Token và Refresh Token")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request,
            HttpServletRequest httpRequest
    ) {
        String ipAddress = httpRequest.getRemoteAddr();
        LoginResponse loginResponse = authService.login(request, ipAddress);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
         authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Đăng ký thành công"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(
            @RequestBody RefreshRequest request,
            HttpServletRequest httpRequest
    ) {
        String ipAddress = httpRequest.getRemoteAddr();
        RefreshResponse refreshResponse = authService.refresh(request, ipAddress);
        return ResponseEntity.ok(refreshResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestBody LogoutRequest request
    ) {
        authService.logout(request);
        return ResponseEntity.ok(Map.of("message", "Đăng xuất thành công"));
    }

}

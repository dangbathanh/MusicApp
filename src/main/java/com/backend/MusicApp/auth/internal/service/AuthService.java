package com.backend.MusicApp.auth.internal.service;

import com.backend.MusicApp.auth.dto.request.LoginRequest;
import com.backend.MusicApp.auth.dto.request.LogoutRequest;
import com.backend.MusicApp.auth.dto.request.RefreshRequest;
import com.backend.MusicApp.auth.dto.request.RegisterRequest;
import com.backend.MusicApp.auth.dto.response.LoginResponse;
import com.backend.MusicApp.auth.dto.response.RefreshResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest, String ipAddress);
    RefreshResponse refresh(RefreshRequest refreshRequest, String ipAddress);
    void register(RegisterRequest registerRequest);
    void logout(LogoutRequest logoutRequest);
}

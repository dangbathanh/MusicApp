package com.backend.MusicApp.auth.dto.response;

public record RegisterResponse(
        String message,
        String status // SUCCESS hoặc ERROR
) {}
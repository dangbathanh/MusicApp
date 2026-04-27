package com.backend.MusicApp.common.exception;

public record ErrorResponse(
//        int status,
//        String message,
//        long timestamp
        int status,
        int code,
        String message
) {}

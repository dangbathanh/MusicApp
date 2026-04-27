package com.backend.MusicApp.streaming.api.dto;

public record AudioResource(
        String url,
        String format,
        Long fileSize,
        Integer sampleRate,
        Integer bitDepth,
        Integer bitrate,
        Integer qualityType
) {}

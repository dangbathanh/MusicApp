package com.backend.MusicApp.catalog.repository.dto;

public record SongFileProjection(
        String url,
        Integer qualityType,  // Dùng để logic Service so sánh (MAX, HIGH, LOW)
        //String qualityLabel, // Dùng để hiển thị (Master, HiFi...)
        String format,
        Long fileSize,
        Integer sampleRate,
        Integer bitDepth,
        Integer bitrate
) {}
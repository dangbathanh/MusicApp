package com.backend.MusicApp.catalog.repository.dto;

public record SongFileProjection(
        String url,
        Integer qualityType,
        //String qualityLabel,
        String format,
        Long fileSize,
        Integer sampleRate,
        Integer bitDepth,
        Integer bitrate
) {}
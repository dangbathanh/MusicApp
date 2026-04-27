package com.backend.MusicApp.playback.dto;

import com.backend.MusicApp.catalog.object.ArtistObject;

import java.util.List;


public record PlayResponse(
        Long id,
        String title,
        String coverUrl,
        List<ArtistObject> artists,
        Integer durationSeconds,
        Long albumId,
        String url,
        String format,
        Long fileSize,
        Integer sampleRate,
        Integer bitDepth,
        Integer bitrate,
        Integer qualityType
) {}

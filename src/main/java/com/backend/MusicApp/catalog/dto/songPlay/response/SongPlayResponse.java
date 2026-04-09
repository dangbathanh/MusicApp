package com.backend.MusicApp.catalog.dto.songPlay.response;

import com.backend.MusicApp.catalog.dto.songPlay.ArtistObject;

import java.time.LocalDate;
import java.util.List;



public record SongPlayResponse(
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
        Integer bitrate
) {}


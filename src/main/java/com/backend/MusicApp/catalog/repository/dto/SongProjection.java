package com.backend.MusicApp.catalog.repository.dto;

import java.time.Instant;
import java.time.LocalDate;

public record SongProjection(
        Long id,
        String title,
        String coverUrl,
        String artistsJson,     // JSON chứa mảng {id, name} của MAIN & FEATURED
        Integer durationSeconds,
        Long albumId
) {}

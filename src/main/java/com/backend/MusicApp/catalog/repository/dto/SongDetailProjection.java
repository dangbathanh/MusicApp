package com.backend.MusicApp.catalog.repository.dto;

import java.time.LocalDate;

public record SongDetailProjection(
        Long id,
        String title,
        Long albumId,
        String albumTitle,
        String artistsJson,
        String albumArtistsJson,
        String coverUrl,
        Integer durationSeconds,
        LocalDate releaseDate
) {}

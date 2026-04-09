package com.backend.MusicApp.catalog.repository.dto;

public record SongCardProjection(
        Long id,
        String title,
        String artistName,
        String coverUrl
) {}

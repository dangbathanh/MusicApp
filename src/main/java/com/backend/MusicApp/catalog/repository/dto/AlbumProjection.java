package com.backend.MusicApp.catalog.repository.dto;

import java.time.Instant;
import java.time.LocalDate;

public record AlbumProjection(
        Long id, String title, Long artistId, String coverUrl,
        LocalDate releaseDate, String albumType, Instant createdAt
) {}
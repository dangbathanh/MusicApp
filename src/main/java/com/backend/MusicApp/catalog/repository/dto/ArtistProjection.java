package com.backend.MusicApp.catalog.repository.dto;

import java.time.Instant;

public record ArtistProjection(
        Long id, String name, String bio, String avatarUrl, Instant createdAt
) {}

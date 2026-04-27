package com.backend.MusicApp.catalog.internal.repository.projection;

import java.time.Instant;

public record ArtistProjection(
        Long id, String name, String bio, String avatarUrl, Instant createdAt
) {}

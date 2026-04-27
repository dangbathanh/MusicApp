package com.backend.MusicApp.catalog.api.dto;

import com.backend.MusicApp.catalog.object.ArtistObject;

import java.util.List;

public record SongMetadata(
        Long id,
        String title,
        String coverUrl,
        List<ArtistObject> artists,
        Integer durationSeconds,
        Long albumId
) {}

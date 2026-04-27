package com.backend.MusicApp.catalog.dto;

import com.backend.MusicApp.catalog.object.ArtistObject;

import java.util.List;

public record SongItem(
        Long id,
        String title,
        String imageUrl,
        List<ArtistObject> artists,
        Long albumId,
        Integer durationSeconds
) implements CatalogItem {}
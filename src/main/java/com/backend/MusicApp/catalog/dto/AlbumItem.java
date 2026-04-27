package com.backend.MusicApp.catalog.dto;

import com.backend.MusicApp.catalog.object.ArtistObject;

import java.util.List;

public record AlbumItem(
        Long id,
        String title,
        String imageUrl,
        List<ArtistObject> artists,
        Integer releaseYear
) implements CatalogItem {}
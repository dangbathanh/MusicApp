package com.backend.MusicApp.catalog.dto;

public record ArtistItem(
        Long id,
        String title,
        String imageUrl
) implements CatalogItem {}

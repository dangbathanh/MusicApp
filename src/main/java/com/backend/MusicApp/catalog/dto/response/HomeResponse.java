package com.backend.MusicApp.catalog.dto.response;

import com.backend.MusicApp.catalog.dto.CatalogItem;

import java.util.List;

public record HomeResponse(
        List<HomeSection> sections
) {
    public record HomeSection(
            String title,
            String type,
            List<CatalogItem> items
    ) {}
}

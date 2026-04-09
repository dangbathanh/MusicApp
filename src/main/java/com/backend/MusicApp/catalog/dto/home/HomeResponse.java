package com.backend.MusicApp.catalog.dto.home;

import java.util.List;

public record HomeResponse(
        List<HomeSection> sections
) {
    public record HomeSection(
            String title,
            String type,
            List<HomeItem> items
    ) {}

    public record HomeItem(
            Long id,
            String title,
            String artistName,
            String imageUrl,
            String itemType
    ) {}
}

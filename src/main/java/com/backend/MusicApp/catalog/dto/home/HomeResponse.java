package com.backend.MusicApp.catalog.dto.home;

import java.util.List;

public record HomeResponse(
        List<HomeSection> sections
) {
    public record HomeSection(
            String title,   // Ví dụ: "Mới phát hành"
            String type,    // Để Android nhận diện: "GRID" hoặc "BANNER"
            List<HomeItem> items
    ) {}

    public record HomeItem(
            Long id,
            String title,
            String artistName, // Lấy từ SongCardProjection.artistName
            String imageUrl,   // Lấy từ SongCardProjection.coverUrl
            String itemType    // "SONG" hoặc "ALBUM"
    ) {}
}

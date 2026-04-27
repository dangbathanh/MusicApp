package com.backend.MusicApp.catalog.dto.response;

import com.backend.MusicApp.catalog.object.ArtistObject;

import java.util.List;

public record AlbumDetailResponse(
        Long id,
        String title,
        List<ArtistObject> albumArtists,
        String coverUrl,
        Long totalDurationSeconds,
        Integer songCount,
        Integer highestQualityType,
        Integer releaseYear,
        List<DiscObject> discs
) {
    public record DiscObject(
            Integer discNumber,
            List<SongItem> songs
    ) {}

    public record SongItem(
            Long id,
            Integer trackNumber,
            String title,
            List<ArtistObject> artists,
            Integer durationSeconds
    ) {}
}



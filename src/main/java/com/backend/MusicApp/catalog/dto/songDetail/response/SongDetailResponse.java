package com.backend.MusicApp.catalog.dto.songDetail.response;

import java.util.List;

public record SongDetailResponse(
        Long id,
        String title,
        String coverUrl,
        List<String> artists,
        List<MusicFileDto> files
) {
    public record MusicFileDto(
            String quality,
            String url,
            String format,
            String bitrate
    ) {}
}

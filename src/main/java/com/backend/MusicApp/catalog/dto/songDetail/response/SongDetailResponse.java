package com.backend.MusicApp.catalog.dto.songDetail.response;

import java.util.List;

public record SongDetailResponse(
        Long id,
        String title,
        String coverUrl,
        List<String> artists, // Tên các nghệ sĩ tham gia
        List<MusicFileDto> files // Các định dạng nhạc (Basic, Lossless, DSD)
) {
    public record MusicFileDto(
            String quality, // 'BASIC', 'PREMIUM'
            String url,     // Link full (đã cộng mediaBaseUrl)
            String format,  // 'mp3', 'flac', 'dsf'
            String bitrate
    ) {}
}

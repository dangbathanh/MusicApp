package com.backend.MusicApp.catalog.dto.songPlay.response;

import com.backend.MusicApp.catalog.dto.songPlay.ArtistObject;

import java.time.LocalDate;
import java.util.List;



public record SongPlayResponse(
        Long id,
        String title,
        String coverUrl,
        List<ArtistObject> artists,
        Integer durationSeconds, // Cần thiết để SeekBar hiển thị ngay lập tức
        Long albumId,         // Để User bấm vào chuyển sang màn hình Album
        String url,
        String format,
        Long fileSize,
        Integer sampleRate,
        Integer bitDepth,
        Integer bitrate
) {}


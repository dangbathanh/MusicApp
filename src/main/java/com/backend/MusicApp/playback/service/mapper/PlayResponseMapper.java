package com.backend.MusicApp.playback.service.mapper;

import com.backend.MusicApp.catalog.api.dto.SongMetadata;
import com.backend.MusicApp.playback.dto.PlayResponse;
import com.backend.MusicApp.streaming.api.dto.AudioResource;
import org.springframework.stereotype.Component;

@Component
public class PlayResponseMapper {

    public PlayResponse toPlayResponse(SongMetadata meta, AudioResource audio) {
        return new PlayResponse(
                meta.id(),
                meta.title(),
                meta.coverUrl(),
                meta.artists(),
                meta.durationSeconds(),
                meta.albumId(),
                audio.url(),
                audio.format(),
                audio.fileSize(),
                audio.sampleRate(),
                audio.bitDepth(),
                audio.bitrate(),
                audio.qualityType()
        );
    }
}

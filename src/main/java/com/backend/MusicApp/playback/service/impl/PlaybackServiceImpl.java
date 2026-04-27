package com.backend.MusicApp.playback.service.impl;

import com.backend.MusicApp.catalog.api.CatalogQueryService;
import com.backend.MusicApp.catalog.api.dto.SongMetadata;
import com.backend.MusicApp.playback.dto.PlayResponse;
import com.backend.MusicApp.playback.service.PlaybackService;
import com.backend.MusicApp.playback.service.mapper.PlayResponseMapper;
import com.backend.MusicApp.streaming.api.StreamingService;
import com.backend.MusicApp.streaming.api.dto.AudioResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaybackServiceImpl implements PlaybackService {
    private final CatalogQueryService catalogQueryService;
    private final StreamingService streamingService;
    private final PlayResponseMapper playResponseMapper;

    @Override
    public PlayResponse play(Long songId, Integer quality) {
        SongMetadata meta = catalogQueryService.getSongMetadata(songId);
        AudioResource audio = streamingService.getAudioResource(songId, quality);
        return playResponseMapper.toPlayResponse(meta, audio);
    }
}

package com.backend.MusicApp.streaming.api;

import com.backend.MusicApp.streaming.api.dto.AudioResource;

public interface StreamingService {
    AudioResource getAudioResource(Long songId, Integer quality);
}

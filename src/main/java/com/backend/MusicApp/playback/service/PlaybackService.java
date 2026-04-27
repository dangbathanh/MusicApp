package com.backend.MusicApp.playback.service;

import com.backend.MusicApp.playback.dto.PlayResponse;

public interface PlaybackService {
    PlayResponse play(Long songId, Integer quality);
}

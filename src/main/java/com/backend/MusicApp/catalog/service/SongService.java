package com.backend.MusicApp.catalog.service;

import com.backend.MusicApp.catalog.dto.songPlay.response.SongPlayResponse;

public interface SongService {
    SongPlayResponse getSongToPlay(Long id, Integer quality);
}

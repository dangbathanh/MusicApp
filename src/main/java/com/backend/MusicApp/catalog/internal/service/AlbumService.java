package com.backend.MusicApp.catalog.internal.service;

import com.backend.MusicApp.catalog.dto.response.AlbumDetailResponse;

public interface AlbumService {
    AlbumDetailResponse getAlbumDetail(Long albumId);
}


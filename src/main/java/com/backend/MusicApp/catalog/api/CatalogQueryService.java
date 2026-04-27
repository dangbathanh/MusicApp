package com.backend.MusicApp.catalog.api;

import com.backend.MusicApp.catalog.api.dto.SongMetadata;

public interface CatalogQueryService {
    SongMetadata getSongMetadata(Long songId);
}

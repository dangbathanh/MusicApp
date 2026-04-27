package com.backend.MusicApp.catalog.internal.repository.projection;

public interface SongProjection {
    Long getId();
    String getTitle();
    String getCoverUrl();
    String getArtists();
    Integer getDurationSeconds();
    Long getAlbumId();
}

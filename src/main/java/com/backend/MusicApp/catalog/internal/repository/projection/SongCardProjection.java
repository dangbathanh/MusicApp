package com.backend.MusicApp.catalog.internal.repository.projection;


public interface SongCardProjection {
    Long getId();
    String getTitle();
    String getCoverUrl();
    String getArtists();
    Long getAlbumId();
    Integer getDurationSeconds();
}

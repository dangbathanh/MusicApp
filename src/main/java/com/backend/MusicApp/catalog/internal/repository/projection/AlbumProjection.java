package com.backend.MusicApp.catalog.internal.repository.projection;

public interface AlbumProjection {
    Long getId();
    String getTitle();
    String getCoverUrl();
    Integer getHighestQualityType();
    String getArtists();
    Integer getReleaseYear();
}

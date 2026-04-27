package com.backend.MusicApp.catalog.internal.repository.projection;



public interface AlbumSongItemProjection {
    Long getId();
    String getTitle();
    Integer getDiscNumber();
    Integer getTrackNumber();
    Integer getDurationSeconds();
    String getArtists();
}

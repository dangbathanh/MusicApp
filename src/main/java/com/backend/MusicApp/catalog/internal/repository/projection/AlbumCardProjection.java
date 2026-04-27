package com.backend.MusicApp.catalog.internal.repository.projection;

import com.backend.MusicApp.catalog.object.ArtistObject;

import java.util.List;

public interface AlbumCardProjection {
    Long getId();
    String getTitle();
    String getCoverUrl();
    String getArtists();
}


package com.backend.MusicApp.catalog.api;

import com.backend.MusicApp.catalog.dto.AlbumItem;
import com.backend.MusicApp.catalog.dto.ArtistItem;
import com.backend.MusicApp.catalog.dto.SongItem;

import java.util.List;

public interface CatalogSearchPort {
    List<SongItem> searchSongs(String q, int limit, int offset);
    List<AlbumItem> searchAlbums(String q, int limit, int offset);
    List<ArtistItem> searchArtists(String q, int limit, int offset);
}

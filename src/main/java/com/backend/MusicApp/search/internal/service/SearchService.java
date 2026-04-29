package com.backend.MusicApp.search.internal.service;

import com.backend.MusicApp.catalog.dto.AlbumItem;
import com.backend.MusicApp.catalog.dto.ArtistItem;
import com.backend.MusicApp.catalog.dto.SongItem;
import com.backend.MusicApp.search.dto.response.TopResultsResponse;

import java.util.List;

public interface SearchService {
    TopResultsResponse searchTopResults(String q);
    List<SongItem> searchSongs(String q, int limit, int offset);
    List<ArtistItem> searchArtists(String q, int limit, int offset);
    List<AlbumItem> searchAlbums(String q, int limit, int offset);
}

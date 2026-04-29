package com.backend.MusicApp.search.dto.response;

import com.backend.MusicApp.catalog.dto.AlbumItem;
import com.backend.MusicApp.catalog.dto.ArtistItem;
import com.backend.MusicApp.catalog.dto.SongItem;

import java.util.List;

public record TopResultsResponse(
        List<SongItem> songs,
        List<ArtistItem> artists,
        List<AlbumItem> albums
) {}

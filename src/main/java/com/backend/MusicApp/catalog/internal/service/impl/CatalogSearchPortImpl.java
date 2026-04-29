package com.backend.MusicApp.catalog.internal.service.impl;

import com.backend.MusicApp.catalog.api.CatalogSearchPort;
import com.backend.MusicApp.catalog.dto.AlbumItem;
import com.backend.MusicApp.catalog.dto.ArtistItem;
import com.backend.MusicApp.catalog.dto.SongItem;
import com.backend.MusicApp.catalog.internal.repository.AlbumRepository;
import com.backend.MusicApp.catalog.internal.repository.ArtistRepository;
import com.backend.MusicApp.catalog.internal.repository.SongRepository;
import com.backend.MusicApp.catalog.internal.service.mapper.CatalogItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogSearchPortImpl implements CatalogSearchPort {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final CatalogItemMapper catalogItemMapper;

    @Override
    public List<SongItem> searchSongs(String q, int limit, int offset) {
        return songRepository.searchByTitle(q, limit, offset)
                .stream()
                .map(catalogItemMapper::toSongItem)
                .toList();
    }

    @Override
    public List<AlbumItem> searchAlbums(String q, int limit, int offset) {
        return albumRepository.searchByTitle(q, limit, offset)
                .stream()
                .map(catalogItemMapper::toAlbumItem)
                .toList();
    }

    @Override
    public List<ArtistItem> searchArtists(String q, int limit, int offset) {
        return artistRepository.searchByName(q, limit, offset)
                .stream()
                .map(catalogItemMapper::toArtistItem)
                .toList();
    }
}

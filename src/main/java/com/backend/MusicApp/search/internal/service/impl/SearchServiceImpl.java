package com.backend.MusicApp.search.internal.service.impl;

import com.backend.MusicApp.catalog.api.CatalogSearchPort;
import com.backend.MusicApp.catalog.dto.AlbumItem;
import com.backend.MusicApp.catalog.dto.ArtistItem;
import com.backend.MusicApp.catalog.dto.SongItem;
import com.backend.MusicApp.search.dto.response.TopResultsResponse;
import com.backend.MusicApp.search.internal.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchService {

    private final CatalogSearchPort catalogSearchPort;
    @Qualifier("ioExecutor")
    private final Executor ioExecutor;

    private static final int TOP_RESULTS_LIMIT = 5;
    private static final TopResultsResponse EMPTY =
            new TopResultsResponse(List.of(), List.of(), List.of());

    @Override
    public TopResultsResponse searchTopResults(String q) {
        String trimmed = q == null ? "" : q.trim();
        if (trimmed.isEmpty()) return EMPTY;

        CompletableFuture<List<SongItem>> songsFuture = CompletableFuture
                .supplyAsync(() -> catalogSearchPort.searchSongs(trimmed, TOP_RESULTS_LIMIT, 0), ioExecutor);
        CompletableFuture<List<ArtistItem>> artistsFuture = CompletableFuture
                .supplyAsync(() -> catalogSearchPort.searchArtists(trimmed, TOP_RESULTS_LIMIT, 0), ioExecutor);
        CompletableFuture<List<AlbumItem>> albumsFuture = CompletableFuture
                .supplyAsync(() -> catalogSearchPort.searchAlbums(trimmed, TOP_RESULTS_LIMIT, 0), ioExecutor);

        CompletableFuture.allOf(songsFuture, artistsFuture, albumsFuture).join();

        return new TopResultsResponse(
                songsFuture.join(),
                artistsFuture.join(),
                albumsFuture.join()
        );
    }

    @Override
    public List<SongItem> searchSongs(String q, int limit, int offset) {
        String trimmed = q == null ? "" : q.trim();
        if (trimmed.isEmpty()) return List.of();
        return catalogSearchPort.searchSongs(trimmed, limit, offset);
    }

    @Override
    public List<ArtistItem> searchArtists(String q, int limit, int offset) {
        String trimmed = q == null ? "" : q.trim();
        if (trimmed.isEmpty()) return List.of();
        return catalogSearchPort.searchArtists(trimmed, limit, offset);
    }

    @Override
    public List<AlbumItem> searchAlbums(String q, int limit, int offset) {
        String trimmed = q == null ? "" : q.trim();
        if (trimmed.isEmpty()) return List.of();
        return catalogSearchPort.searchAlbums(trimmed, limit, offset);
    }
}

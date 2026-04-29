package com.backend.MusicApp.search.controller;

import com.backend.MusicApp.catalog.dto.AlbumItem;
import com.backend.MusicApp.catalog.dto.ArtistItem;
import com.backend.MusicApp.catalog.dto.SongItem;
import com.backend.MusicApp.search.dto.response.TopResultsResponse;
import com.backend.MusicApp.search.internal.service.SearchService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@Validated
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<TopResultsResponse> searchTopResults(
            @RequestParam @NotBlank @Size(max = 100) String q
    ) {
        return ResponseEntity.ok(searchService.searchTopResults(q));
    }

    @GetMapping("/songs")
    public ResponseEntity<List<SongItem>> searchSongs(
            @RequestParam @NotBlank @Size(max = 100) String q,
            @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
            @RequestParam(defaultValue = "0")  @Min(0) int offset
    ) {
        return ResponseEntity.ok(searchService.searchSongs(q, limit, offset));
    }

    @GetMapping("/artists")
    public ResponseEntity<List<ArtistItem>> searchArtists(
            @RequestParam @NotBlank @Size(max = 100) String q,
            @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
            @RequestParam(defaultValue = "0")  @Min(0) int offset
    ) {
        return ResponseEntity.ok(searchService.searchArtists(q, limit, offset));
    }

    @GetMapping("/albums")
    public ResponseEntity<List<AlbumItem>> searchAlbums(
            @RequestParam @NotBlank @Size(max = 100) String q,
            @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
            @RequestParam(defaultValue = "0")  @Min(0) int offset
    ) {
        return ResponseEntity.ok(searchService.searchAlbums(q, limit, offset));
    }
}

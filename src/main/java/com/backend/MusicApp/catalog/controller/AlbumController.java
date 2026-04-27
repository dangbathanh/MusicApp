package com.backend.MusicApp.catalog.controller;

import com.backend.MusicApp.catalog.dto.response.AlbumDetailResponse;
import com.backend.MusicApp.catalog.internal.service.AlbumService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
@Tag(name = "Album Catalog", description = "APIs for browsing album metadata") // Dùng cho Swagger/OpenAPI nếu có
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDetailResponse> getAlbumDetail(@PathVariable Long id) {
        AlbumDetailResponse response = albumService.getAlbumDetail(id);
        return ResponseEntity.ok(response);
    }
}

package com.backend.MusicApp.catalog.controller;

import com.backend.MusicApp.catalog.dto.home.HomeResponse;
import com.backend.MusicApp.catalog.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/home")
    public ResponseEntity<HomeResponse> getHome() {
        return ResponseEntity.ok(catalogService.getHomePageData());
    }
}

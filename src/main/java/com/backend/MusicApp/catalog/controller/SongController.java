package com.backend.MusicApp.catalog.controller;

import com.backend.MusicApp.catalog.dto.songPlay.response.SongPlayResponse;
import com.backend.MusicApp.catalog.service.SongService;
import com.backend.MusicApp.catalog.dto.songDetail.response.SongDetailResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
@Validated
public class SongController {

    private final SongService songService;

    @GetMapping("/{id}/play")
    public ResponseEntity<SongPlayResponse> getSongToPlay(
            @PathVariable Long id,
            @RequestParam(name = "quality", defaultValue = "1")
            @Min(value = 1, message = "Quality thấp nhất là 1")
            @Max(value = 3, message = "Quality cao nhất là 3") Integer quality
    ) {
        SongPlayResponse response = songService.getSongToPlay(id, quality);

        return ResponseEntity.ok(response);
    }
}
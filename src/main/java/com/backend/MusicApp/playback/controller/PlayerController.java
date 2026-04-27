package com.backend.MusicApp.playback.controller;

import com.backend.MusicApp.playback.dto.PlayResponse;
import com.backend.MusicApp.playback.service.PlaybackService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playback")
@RequiredArgsConstructor
@Validated
public class PlayerController {

    private final PlaybackService playbackService;

    @GetMapping("/songs/{id}/play")
    public ResponseEntity<PlayResponse> play(
            @PathVariable Long id,
            @RequestParam(name = "quality", defaultValue = "1")
            @Min(value = 1, message = "Quality thấp nhất là 1")
            @Max(value = 3, message = "Quality cao nhất là 3") Integer quality
    ) {
        return ResponseEntity.ok(playbackService.play(id, quality));
    }
}

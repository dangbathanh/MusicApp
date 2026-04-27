package com.backend.MusicApp.catalog.internal.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongArtistId {
    private Long songId;
    private Long artistId;
    private String role;
}

package com.backend.MusicApp.catalog.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumArtistId implements Serializable {
    private Long albumId;
    private Long artistId;
}

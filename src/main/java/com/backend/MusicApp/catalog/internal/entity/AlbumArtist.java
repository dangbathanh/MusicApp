package com.backend.MusicApp.catalog.internal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "album_artists")
@Getter @Setter @NoArgsConstructor
public class AlbumArtist {
    @EmbeddedId
    private AlbumArtistId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("albumId")
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("artistId")
    @JoinColumn(name = "artist_id")
    private Artist artist;

    private Integer displayOrder;
}

package com.backend.MusicApp.catalog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "song_artists")
@Getter
@Setter @NoArgsConstructor
public class SongArtist {
    @EmbeddedId
    private SongArtistId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("songId")
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("artistId")
    @JoinColumn(name = "artist_id")
    private Artist artist;

    private String role; // 'MAIN', 'FEATURING'
}
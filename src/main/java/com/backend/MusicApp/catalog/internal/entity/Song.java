package com.backend.MusicApp.catalog.internal.entity;

import com.backend.MusicApp.catalog.object.ArtistObject;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "songs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @Column(name = "track_number")
    private Integer trackNumber;

    @Column(name = "disc_number")
    private Integer discNumber;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "artists_jsonb", columnDefinition = "jsonb")
    private List<ArtistObject> artists;
}
package com.backend.MusicApp.catalog.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List; // Cần import thêm cái này

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

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column(name = "view_count", columnDefinition = "int8 default 0")
    private Long viewCount;
}
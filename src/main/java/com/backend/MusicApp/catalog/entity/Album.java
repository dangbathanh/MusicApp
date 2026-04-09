package com.backend.MusicApp.catalog.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "albums")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "album_type") //'ALBUM', 'SINGLE', 'EP'
    private String albumType;

    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "timestamptz")
    private Instant createdAt;
}
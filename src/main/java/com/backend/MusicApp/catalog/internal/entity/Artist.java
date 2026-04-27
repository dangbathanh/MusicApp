package com.backend.MusicApp.catalog.internal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artists")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "text")
    private String bio;

    @Column(name = "avatar_url")
    private String avatarUrl;
}
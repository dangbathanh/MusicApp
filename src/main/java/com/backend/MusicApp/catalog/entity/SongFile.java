package com.backend.MusicApp.catalog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "song_files")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SongFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    // Fix lỗi: found [int8], but expecting [varchar] hoặc [integer]
    @Column(name = "quality_type", columnDefinition = "int8")
    private Integer qualityType;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    private String format;

    @Column(name = "sample_rate")
    private Integer sampleRate;

    // Fix lỗi: found [int2], but expecting [integer]
    @Column(name = "bit_depth", columnDefinition = "int2")
    private Integer bitDepth;

    private Integer bitrate;
}
package com.backend.MusicApp.streaming.internal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "song_files")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SongFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "song_id", nullable = false)
    private Long songId;

    @Column(name = "quality_type", columnDefinition = "int2")
    private Integer qualityType;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    private String format;

    @Column(name = "sample_rate")
    private Integer sampleRate;

    @Column(name = "bit_depth", columnDefinition = "int2")
    private Integer bitDepth;

    private Integer bitrate;
}
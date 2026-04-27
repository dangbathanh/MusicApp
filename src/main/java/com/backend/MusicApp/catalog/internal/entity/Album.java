package com.backend.MusicApp.catalog.internal.entity;

import com.backend.MusicApp.catalog.object.ArtistObject;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


import java.sql.Types;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

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

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "artists_jsonb", columnDefinition = "jsonb")
    private List<ArtistObject> artists;

    @Column(name = "highest_quality_type")
    @JdbcTypeCode(Types.SMALLINT)
    private Integer highestQualityType;

}
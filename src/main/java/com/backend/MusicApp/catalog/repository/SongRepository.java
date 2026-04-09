package com.backend.MusicApp.catalog.repository;

import com.backend.MusicApp.catalog.entity.Song;
import com.backend.MusicApp.catalog.repository.dto.SongCardProjection;
import com.backend.MusicApp.catalog.repository.dto.SongProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query(value = """
        SELECT 
            s.id as id, 
            s.title as title, 
            COALESCE(string_agg(a.name, ', ' ORDER BY sa.role), 'Unknown Artist') as "artistName", 
            s.cover_url as "coverUrl"
        FROM songs s
        LEFT JOIN song_artists sa ON s.id = sa.song_id
        LEFT JOIN artists a ON sa.artist_id = a.id
        GROUP BY s.id, s.title, s.cover_url, s.release_date
        ORDER BY s.release_date DESC, s.id DESC
        LIMIT :limit
    """, nativeQuery = true)
    List<SongCardProjection> findTopNewest(@Param("limit") int limit);

    @Query(value = """
        SELECT 
            s.id as id, 
            s.title as title, 
            s.cover_url as "coverUrl", -- Vị trí 3
            (SELECT json_agg(json_build_object('id', a.id, 'name', a.name) ORDER BY sa.role)
             FROM song_artists sa
             JOIN artists a ON sa.artist_id = a.id
             WHERE sa.song_id = s.id 
             AND sa.role IN ('MAIN', 'FEATURED')) as "artistsJson", 
            s.duration_seconds as "durationSeconds", 
            s.album_id as "albumId" 
        FROM songs s
        WHERE s.id = :id
    """, nativeQuery = true)
    Optional<SongProjection> findSongMetadataById(@Param("id") Long id);
}
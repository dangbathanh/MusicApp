package com.backend.MusicApp.catalog.internal.repository;

import com.backend.MusicApp.catalog.internal.entity.Song;
import com.backend.MusicApp.catalog.internal.repository.projection.SongCardProjection;
import com.backend.MusicApp.catalog.internal.repository.projection.SongProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
     @Query(value = """
         SELECT id, title, cover_url as coverUrl,
         artists_jsonb as artists,
         album_id as albumId,
         duration_seconds as durationSeconds
         FROM songs
         ORDER BY created_at DESC, id DESC
         LIMIT :limit
         """, nativeQuery = true)
     List<SongCardProjection> findTopNewest(@Param("limit") int limit);


    @Query(value = """
        SELECT id, title, cover_url as coverUrl, artists_jsonb as artists, 
        duration_seconds as durationSeconds, album_id as albumId
        FROM songs 
        WHERE id = :id
        """, nativeQuery = true)
    Optional<SongProjection> findSongMetadataById(@Param("id") Long id);

    @Query(value = """
        SELECT id, title, cover_url as coverUrl, artists_jsonb as artists,
               album_id as albumId, duration_seconds as durationSeconds
        FROM songs
        WHERE title ILIKE '%' || :q || '%'
        ORDER BY id
        LIMIT :limit OFFSET :offset
        """, nativeQuery = true)
    List<SongCardProjection> searchByTitle(@Param("q") String q,
                                           @Param("limit") int limit,
                                           @Param("offset") int offset);
}
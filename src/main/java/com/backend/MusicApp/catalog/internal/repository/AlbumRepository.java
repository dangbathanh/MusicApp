package com.backend.MusicApp.catalog.internal.repository;

import com.backend.MusicApp.catalog.internal.entity.Album;
import com.backend.MusicApp.catalog.internal.repository.projection.AlbumCardProjection;
import com.backend.MusicApp.catalog.internal.repository.projection.AlbumProjection;
import com.backend.MusicApp.catalog.internal.repository.projection.AlbumSongItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query(value = """
        SELECT id, title, cover_url as coverUrl, artists_jsonb as artists
        FROM albums 
        ORDER BY release_date DESC, id DESC 
        LIMIT :limit
        """, nativeQuery = true)
    List<AlbumCardProjection> findTopNewest(@Param("limit") int limit);

    @Query(value = """
            SELECT id, title, cover_url as coverUrl, artists_jsonb as artists
            FROM albums
            WHERE id >= (SELECT floor(random() * (SELECT max(id) FROM albums)))
            LIMIT :limit
        """, nativeQuery = true)
    List<AlbumCardProjection> findRandomAlbums(@Param("limit") int limit);

    @Query(value = """
    SELECT 
        id, 
        title, 
        cover_url as coverUrl, 
        highest_quality_type as highestQualityType, 
        artists_jsonb as artists,
        EXTRACT(YEAR FROM release_date) AS releaseYear
    FROM albums 
    WHERE id = :id
    """, nativeQuery = true)
    Optional<AlbumProjection> findAlbumDetailById(@Param("id") Long id);

    @Query(value = """
    SELECT 
        id, 
        title, 
        track_number as trackNumber, 
        disc_number as discNumber, 
        duration_seconds as durationSeconds, 
        artists_jsonb as artists
    FROM songs
    WHERE album_id = :albumId
    ORDER BY disc_number ASC, track_number ASC
    """, nativeQuery = true)
    List<AlbumSongItemProjection> findAllSongsInAlbum(@Param("albumId") Long albumId);

    @Query(value = """
        SELECT id, title, cover_url as coverUrl, artists_jsonb as artists
        FROM albums
        WHERE title ILIKE '%' || :q || '%'
        ORDER BY id
        LIMIT :limit OFFSET :offset
        """, nativeQuery = true)
    List<AlbumCardProjection> searchByTitle(@Param("q") String q,
                                            @Param("limit") int limit,
                                            @Param("offset") int offset);
}

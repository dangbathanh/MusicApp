package com.backend.MusicApp.catalog.internal.repository;

import com.backend.MusicApp.catalog.internal.entity.Artist;
import com.backend.MusicApp.catalog.internal.repository.projection.ArtistCardProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query(value = """
        SELECT id, name, avatar_url as avatarUrl
        FROM artists
        WHERE name ILIKE '%' || :q || '%'
        ORDER BY id
        LIMIT :limit OFFSET :offset
        """, nativeQuery = true)
    List<ArtistCardProjection> searchByName(@Param("q") String q,
                                            @Param("limit") int limit,
                                            @Param("offset") int offset);
}
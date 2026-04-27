package com.backend.MusicApp.catalog.internal.repository;

import com.backend.MusicApp.catalog.internal.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
//    @Query(value = """
//    SELECT id, name, avatar_url as avatarUrl
//    FROM artists ORDER BY ... LIMIT :limit
//    """, nativeQuery = true)
//    List<ArtistCardProjection> findTopArtists(@Param("limit") int limit);
}
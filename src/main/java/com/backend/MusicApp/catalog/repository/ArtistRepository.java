package com.backend.MusicApp.catalog.repository;

import com.backend.MusicApp.catalog.entity.Artist;
import com.backend.MusicApp.catalog.repository.dto.ArtistProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("""
        SELECT new com.backend.MusicApp.catalog.repository.dto.ArtistProjection(
            a.id, a.name, null, a.avatarUrl, a.createdAt
        )
        FROM Artist a
    """)
    List<ArtistProjection> findAllSummary();

    @Query("""
        SELECT new com.backend.MusicApp.catalog.repository.dto.ArtistProjection(
            a.id, a.name, a.bio, a.avatarUrl, a.createdAt
        )
        FROM Artist a 
        WHERE a.id = :id
    """)
    Optional<ArtistProjection> findDetailById(@Param("id") Long id);
}
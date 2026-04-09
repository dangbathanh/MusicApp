package com.backend.MusicApp.catalog.repository;

import com.backend.MusicApp.catalog.entity.SongFile;
import com.backend.MusicApp.catalog.repository.dto.SongFileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongFileRepository extends JpaRepository<SongFile, Long> {

    @Query("""
        SELECT new com.backend.MusicApp.catalog.repository.dto.SongFileProjection(
            sf.filePath, 
            sf.qualityType, 
            sf.format, 
            sf.fileSize, 
            sf.sampleRate, 
            sf.bitDepth, 
            sf.bitrate
        )
        FROM SongFile sf
        WHERE sf.song.id = :songId
        ORDER BY sf.qualityType DESC
    """)
    List<SongFileProjection> findAllBySongId(@Param("songId") Long songId);

    @Query("""
        SELECT new com.backend.MusicApp.catalog.repository.dto.SongFileProjection(
            sf.filePath, 
            sf.qualityType, 
            sf.format, 
            sf.fileSize, 
            sf.sampleRate, 
            sf.bitDepth, 
            sf.bitrate
        )
        FROM SongFile sf
        WHERE sf.song.id = :songId AND sf.qualityType = :qualityType
    """)
    Optional<SongFileProjection> findBySongIdAndQuality(
            @Param("songId") Long songId,
            @Param("qualityType") Integer qualityType
    );
}
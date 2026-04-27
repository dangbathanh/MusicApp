package com.backend.MusicApp.streaming.internal.repository;

import com.backend.MusicApp.streaming.internal.entity.SongFile;
import com.backend.MusicApp.streaming.internal.repository.projection.SongFileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SongFileRepository extends JpaRepository<SongFile, Long> {
    @Query(value = """
    SELECT 
        file_path as url,
        quality_type as qualityType, 
        format,
        file_size as fileSize,
        sample_rate as sampleRate,
        bit_depth::INTEGER as bitDepth, 
        bitrate
    FROM song_files
    WHERE song_id = :songId AND quality_type <= :quality
    ORDER BY quality_type DESC
    LIMIT 1
    """, nativeQuery = true)
    Optional<SongFileProjection> findBySongIdAndQuality(
            @Param("songId") Long songId,
            @Param("quality") Integer quality
    );

}
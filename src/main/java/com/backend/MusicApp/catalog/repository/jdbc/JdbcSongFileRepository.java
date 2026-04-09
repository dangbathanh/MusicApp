package com.backend.MusicApp.catalog.repository.jdbc;

import com.backend.MusicApp.catalog.repository.SongFileRepository;
import com.backend.MusicApp.catalog.repository.dto.SongFileProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
@Repository
@RequiredArgsConstructor
public class JdbcSongFileRepository implements SongFileRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<SongFileProjection> songFileRowMapper = (rs, rowNum) -> new SongFileProjection(
            rs.getString("url"),
            rs.getInt("quality_type"),
            rs.getString("format"),
            rs.getLong("file_size"),
            rs.getInt("sample_rate"),
            rs.getInt("bit_depth"),
            rs.getInt("bitrate")
    );

    @Override
    public List<SongFileProjection> findAllBySongId(Long songId) {
        // Lấy tất cả các phiên bản chất lượng của một bài hát
        String sql = """
                    SELECT file_path as url, quality_type, format, file_size, 
                           sample_rate, bit_depth, bitrate
                    FROM song_files
                    WHERE song_id = ?
                    ORDER BY quality_type DESC
                """;
        return jdbcTemplate.query(sql, songFileRowMapper, songId);
    }

    @Override
    public Optional<SongFileProjection> findBySongIdAndQuality(Long songId, String qualityType) {
        String sql = """
                    SELECT id, quality_type, quality_label, file_path, file_size, format, bitrate_info
                    FROM song_files
                    WHERE song_id = ? AND quality_type = ?
                """;
        return jdbcTemplate.query(sql, songFileRowMapper, songId, qualityType).stream().findFirst();
    }
}
*/
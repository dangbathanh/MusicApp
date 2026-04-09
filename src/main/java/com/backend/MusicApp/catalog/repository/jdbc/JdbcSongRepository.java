package com.backend.MusicApp.catalog.repository.jdbc;

import com.backend.MusicApp.catalog.entity.Song;
import com.backend.MusicApp.catalog.entity.SongFile;
import com.backend.MusicApp.catalog.repository.SongRepository;
import com.backend.MusicApp.catalog.repository.dto.SongCardProjection;
import com.backend.MusicApp.catalog.repository.dto.SongDetailProjection;
import com.backend.MusicApp.catalog.repository.dto.SongProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
@Repository
@RequiredArgsConstructor
public class JdbcSongRepository implements SongRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<SongCardProjection> cardMapper = (rs, rowNum) -> new SongCardProjection(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("artist_name"),
            rs.getString("cover_url")
    );

    // 2. Mapper cho Player (Nghe nhạc chi tiết)
    private final RowMapper<SongProjection> songMapper = (rs, rowNum) -> new SongProjection(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("cover_url"),
            rs.getString("artists_json"),
            rs.getInt("duration_seconds"),
            rs.getLong("album_id")
    );

    @Override
    public List<SongCardProjection> findTopNewest(int limit) {
        String sql = """
            SELECT s.id, s.title, s.cover_url,
                   COALESCE(string_agg(a.name, ', ' ORDER BY sa.role), 'Unknown Artist') as artist_name
            FROM songs s
            LEFT JOIN song_artists sa ON s.id = sa.song_id
            LEFT JOIN artists a ON sa.artist_id = a.id
            GROUP BY s.id, s.title, s.cover_url, s.release_date
            ORDER BY s.release_date DESC, s.id DESC
            LIMIT ?
        """;
        return jdbcTemplate.query(sql, cardMapper, limit);
    }

    @Override
    public Optional<SongProjection> findSongMetadataById(Long id) {
        // SQL tối giản cho Player (Bỏ join Album, chỉ lấy Metadata bài hát)
        String sql = """
            SELECT s.id, s.title, s.cover_url, s.duration_seconds, s.album_id,
                   (SELECT json_agg(json_build_object('id', a.id, 'name', a.name) ORDER BY sa.role)
                    FROM song_artists sa
                    JOIN artists a ON sa.artist_id = a.id
                    WHERE sa.song_id = s.id 
                    AND sa.role IN ('MAIN', 'FEATURED')) as artists_json
            FROM songs s
            WHERE s.id = ?
        """;
        return jdbcTemplate.query(sql, songMapper, id).stream().findFirst();
    }

    @Override
    public List<SongProjection> findByAlbumId(Long albumId) {
        String sql = """
            SELECT s.id, s.title, s.album_id, al.title as album_title, 
                   s.cover_url, s.duration_seconds, s.release_date,
                   (SELECT json_agg(json_build_object('id', a.id, 'name', a.name) ORDER BY sa.role)
                    FROM song_artists sa
                    JOIN artists a ON sa.artist_id = a.id
                    WHERE sa.song_id = s.id 
                    AND sa.role IN ('MAIN', 'FEATURED')) as artists_json
            FROM songs s
            JOIN albums al ON s.album_id = al.id
            WHERE s.album_id = ?
            ORDER BY s.track_number ASC
        """;
        return jdbcTemplate.query(sql, songMapper, albumId);
    }

    @Override
    public void incrementViewCount(Long id) {
        jdbcTemplate.update("UPDATE songs SET view_count = view_count + 1 WHERE id = ?", id);
    }

    @Override
    public List<String> findArtistNamesBySongId(Long songId) {
        String sql = """
        SELECT a.name 
        FROM artists a
        JOIN song_artists sa ON a.id = sa.artist_id
        WHERE sa.song_id = ?
        ORDER BY sa.role -- Giả sử bạn muốn Main Artist lên đầu
    """;
        return jdbcTemplate.queryForList(sql, String.class, songId);
    }

}
*/

package com.backend.MusicApp.catalog.repository.jdbc;

import com.backend.MusicApp.catalog.repository.ArtistRepository;
import com.backend.MusicApp.catalog.repository.dto.ArtistProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
@Repository
@RequiredArgsConstructor
public class JdbcArtistRepository implements ArtistRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ArtistProjection> findAllSummary() {
        // Tuyệt đối không lấy cột 'bio' ở đây để tiết kiệm RAM/Network
        String sql = "SELECT id, name, avatar_url, created_at FROM artists";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new ArtistProjection(
                rs.getLong("id"),
                rs.getString("name"),
                null, // Bio set null
                rs.getString("avatar_url"),
                rs.getTimestamp("created_at").toInstant()
        ));
    }

    @Override
    public Optional<ArtistProjection> findDetailById(Long id) {
        String sql = "SELECT * FROM artists WHERE id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new ArtistProjection(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("bio"), // Lấy đầy đủ bio
                rs.getString("avatar_url"),
                rs.getTimestamp("created_at").toInstant()
        ), id).stream().findFirst();
    }
}
*/
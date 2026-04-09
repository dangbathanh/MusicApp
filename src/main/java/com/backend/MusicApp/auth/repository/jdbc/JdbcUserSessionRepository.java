package com.backend.MusicApp.auth.repository.jdbc;
import com.backend.MusicApp.auth.entity.UserSession;
import com.backend.MusicApp.auth.repository.UserSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

//@Repository
//@AllArgsConstructor
//public class JdbcUserSessionRepository implements UserSessionRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    private final RowMapper<UserSession> sessionRowMapper = new RowMapper<>() {
//        @Override
//        public UserSession mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new UserSession(
//                    rs.getLong("user_id"),
//                    rs.getString("refresh_token_hash"),
//                    rs.getString("device_id"),
//                    rs.getString("device_name"),
//                    rs.getString("ip_address"),
//                    rs.getTimestamp("created_at").toInstant(),
//                    rs.getTimestamp("expires_at").toInstant(),
//                    rs.getTimestamp("revoked_at") != null
//                            ? rs.getTimestamp("revoked_at").toInstant()
//                            : null
//
//            );
//        }
//    };
//
//    @Override
//    public void save(UserSession session) {
//        String sql = """
//                INSERT INTO user_sessions
//                (user_id, refresh_token_hash, device_id, device_name, ip_address, expires_at)
//                VALUES (?, ?, ?, ?, ?, ?)
//                """;
//
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setLong(1, session.getUserId());
//            ps.setString(2, session.getRefreshTokenHash());
//            ps.setString(3, session.getDeviceId());
//            ps.setString(4, session.getDeviceName());
//            ps.setString(5, session.getIpAddress());
//            ps.setTimestamp(6, Timestamp.from(session.getExpiresAt()));
//            return ps;
//        });
//    }
//
//    @Override
//    public Optional<UserSession> findValidByRefreshTokenHash(String refreshTokenHash) {
//        String sql = """
//                SELECT *
//                FROM user_sessions
//                WHERE refresh_token_hash = ?
//                  AND revoked_at IS NULL
//                  AND expires_at > now()
//                """;
//
//        return jdbcTemplate.query(sql, sessionRowMapper, refreshTokenHash)
//                .stream()
//                .findFirst();
//    }
//
//    @Override
//    public void revokeById(Long sessionId) {
//        String sql = """
//                UPDATE user_sessions
//                SET revoked_at = now()
//                WHERE id = ?
//                """;
//
//        jdbcTemplate.update(sql, sessionId);
//    }
//
//    @Override
//    public void revokeAllByUserId(Long userId) {
//        String sql = """
//                UPDATE user_sessions
//                SET revoked_at = now()
//                WHERE user_id = ?
//                  AND revoked_at IS NULL
//                """;
//
//        jdbcTemplate.update(sql, userId);
//    }
//}

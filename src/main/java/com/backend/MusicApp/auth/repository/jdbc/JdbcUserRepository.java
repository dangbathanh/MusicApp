package com.backend.MusicApp.auth.repository.jdbc;

import com.backend.MusicApp.auth.entity.User;
import com.backend.MusicApp.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

//@Repository
//@AllArgsConstructor
//public class JdbcUserRepository implements UserRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    private final RowMapper<User> userRowMapper = new RowMapper<>() {
//        @Override
//        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new User(
//                    rs.getLong("id"),
//                    rs.getString("username"),
//                    rs.getString("email"),
//                    rs.getString("password"),
//                    rs.getString("role"),
//                    rs.getString("status"),
//                    rs.getTimestamp("created_at").toInstant()
//            );
//        }
//    };
//
//    @Override
//    public Optional<User> findByEmail(String email) {
//        String sql = """
//                SELECT *
//                FROM users
//                WHERE email = ?
//                """;
//
//        return jdbcTemplate.query(sql, userRowMapper, email)
//                .stream()
//                .findFirst();
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        String sql = """
//                SELECT *
//                FROM users
//                WHERE id = ?
//                """;
//
//        return jdbcTemplate.query(sql, userRowMapper, id)
//                .stream()
//                .findFirst();
//    }
//
//    @Override
//    public boolean existsByEmail(String email) {
//        Integer count = jdbcTemplate.queryForObject(
//                "SELECT COUNT(1) FROM users WHERE email = ?",
//                Integer.class,
//                email
//        );
//        return count != null && count > 0;
//    }
//
//    @Override
//    public Long save(User user) {
//        String sql = """
//                INSERT INTO users (username, email, password, role, status)
//                VALUES (?, ?, ?, ?, ?)
//                RETURNING id
//                """;
//
//        return jdbcTemplate.queryForObject(
//                sql,
//                Long.class,
//                user.getEmail(),
//                user.getPassword(),
//                user.getRole(),
//                user.getStatus()
//        );
//    }
//
//    @Override
//    public Long saveNonPostgre(User user) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        String sql = """
//                     INSERT INTO users (username, email, password, role, status)
//                            VALUES (?, ?, ?, ?, ?)
//                     """;
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement(
//                    sql,
//                    new String[] {"id"}
//            );
//            ps.setString(1,user.getUsername());
//            ps.setString(2, user.getEmail());
//            ps.setString(3, user.getPassword());
//            ps.setString(4, user.getRole());
//            ps.setString(5, user.getStatus());
//            return ps;
//        }, keyHolder);
//
//        return Objects.requireNonNull(keyHolder.getKey()).longValue();
//    }
//}

package com.backend.MusicApp.auth.repository;
import com.backend.MusicApp.auth.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    @Query("SELECT s FROM UserSession s WHERE s.refreshTokenHash = :hash " +
            "AND s.revokedAt IS NULL AND s.expiresAt > CURRENT_TIMESTAMP")
    Optional<UserSession> findValidByRefreshTokenHash(@Param("hash") String hash);

    // 2. Thu hồi session theo ID (Cập nhật thời gian revoked_at)
    @Modifying
    @Transactional
    @Query("UPDATE UserSession s SET s.revokedAt = CURRENT_TIMESTAMP WHERE s.id = :sessionId")
    void revokeById(@Param("sessionId") Long sessionId);

    // 3. Thu hồi tất cả session của một User (Dựa trên quan hệ trong Entity)
    @Modifying
    @Transactional
    @Query("UPDATE UserSession s SET s.revokedAt = CURRENT_TIMESTAMP " +
            "WHERE s.user.id = :userId AND s.revokedAt IS NULL")
    void revokeAllByUserId(@Param("userId") Long userId);
}

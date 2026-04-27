package com.backend.MusicApp.auth.internal.repository;

import com.backend.MusicApp.auth.internal.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
    @Query("""
            SELECT s FROM UserSession s WHERE s.refreshTokenHash = :hash
            AND s.revokedAt IS NULL AND s.expiresAt > CURRENT_TIMESTAMP
            """)
    Optional<UserSession> findValidByRefreshTokenHash(@Param("hash") String hash);

    @Modifying
    @Transactional
    @Query("UPDATE UserSession s SET s.revokedAt = CURRENT_TIMESTAMP WHERE s.id = :sessionId")
    void revokeById(@Param("sessionId") Long sessionId);

    @Modifying
    @Transactional
    @Query("""
    UPDATE UserSession s SET s.revokedAt = CURRENT_TIMESTAMP 
            WHERE s.userId = :userId AND s.revokedAt IS NULL
    """)
    void revokeAllByUserId(@Param("userId") Long userId);

    Optional<UserSession> findByJti(UUID jti);
}

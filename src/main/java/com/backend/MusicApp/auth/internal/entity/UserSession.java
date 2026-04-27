package com.backend.MusicApp.auth.internal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jti", nullable = false, unique = true)
    private UUID jti;

    @Column(name = "refresh_token_hash")
    private String refreshTokenHash;

    private String deviceId;
    private String deviceName;
    private String ipAddress;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant expiresAt;
    private Instant revokedAt;

    @Column(updatable = false, nullable = false)
    private Instant issuedAt;

    //dùng userId thay vì @ManyToOne User(vi phạm modular monolith vì gọi entity User)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    public boolean isActive() {
        return revokedAt == null && expiresAt.isAfter(Instant.now());
    }
}

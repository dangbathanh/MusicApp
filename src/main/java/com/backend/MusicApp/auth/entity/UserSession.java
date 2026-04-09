package com.backend.MusicApp.auth.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "refresh_token_hash")
    private String refreshTokenHash;
    private String deviceId;
    private String deviceName;
    private String ipAddress;
    @Column(updatable = false)
    private Instant createdAt;
    private Instant expiresAt;
    private Instant revokedAt;


    public boolean isActive() {
        return revokedAt == null && expiresAt.isAfter(Instant.now());
    }
}

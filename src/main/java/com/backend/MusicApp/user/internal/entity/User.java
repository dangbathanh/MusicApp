package com.backend.MusicApp.user.internal.entity;

import com.backend.MusicApp.user.enums.UserRole;
import com.backend.MusicApp.user.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @NotBlank
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;
}

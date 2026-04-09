package com.backend.MusicApp.auth.service.impl;

import com.backend.MusicApp.auth.crypto.PasswordHasher;
import com.backend.MusicApp.auth.crypto.TokenHasher;
import com.backend.MusicApp.auth.dto.command.LoginCommand;
import com.backend.MusicApp.auth.dto.command.LogoutCommand;
import com.backend.MusicApp.auth.dto.command.RefreshCommand;
import com.backend.MusicApp.auth.dto.command.RegisterCommand;
import com.backend.MusicApp.auth.dto.result.LoginResult;
import com.backend.MusicApp.auth.dto.result.RegisterResult;
import com.backend.MusicApp.auth.dto.result.TokenResult;
import com.backend.MusicApp.auth.entity.User;
import com.backend.MusicApp.auth.entity.UserSession;
import com.backend.MusicApp.auth.repository.UserRepository;
import com.backend.MusicApp.auth.repository.UserSessionRepository;
import com.backend.MusicApp.auth.service.AuthService;
import com.backend.MusicApp.auth.time.TimeProvider;
import com.backend.MusicApp.auth.token.TokenGenerator;
import com.backend.MusicApp.common.exception.LoginFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserSessionRepository sessionRepository;
    private final PasswordHasher passwordHasher;
    private final TokenHasher tokenHasher;
    private final TokenGenerator tokenGenerator;
    private final TimeProvider timeProvider;

    @Transactional
    @Override
    public LoginResult login(LoginCommand command){
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new LoginFailedException("Invalid email or password"));
        if (!"ACTIVE".equals(user.getStatus())) {
            throw new LoginFailedException("User is not active");
        }
        if (!passwordHasher.matches(command.password(), user.getPassword())) {
            throw new LoginFailedException("Invalid email or password");
        }

        sessionRepository.revokeAllByUserId(user.getId());

        String accessToken =
                tokenGenerator.generateAccessToken(user.getId(), user.getRole());

        String refreshToken =
                tokenGenerator.generateRefreshToken(user.getId());

        String refreshTokenHash = tokenHasher.hash(refreshToken);
        Instant now = timeProvider.now();
        UserSession session = new UserSession();
        session.setUser(user);
        session.setRefreshTokenHash(refreshTokenHash);
        session.setDeviceId(command.deviceId());
        session.setDeviceName(command.deviceName());
        session.setIpAddress(command.ipAddress());
        session.setExpiresAt(now.plus(30, ChronoUnit.DAYS));

        sessionRepository.save(session);
        return new LoginResult(accessToken, refreshToken, user.getUsername(), user.getEmail());
    }



    @Transactional
    @Override
    public RegisterResult register(RegisterCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new RuntimeException("Email already exists");
        }
        String passwordHash = passwordHasher.hash(command.password());


        User user = new User();
        user.setUsername(command.username());
        user.setEmail(command.email());
        user.setPassword(passwordHash);
        user.setRole("USER");
        user.setStatus("ACTIVE");
        User savedUser = userRepository.save(user);
        return new RegisterResult(savedUser.getId(), savedUser.getEmail());
    }

    @Transactional
    @Override
    public TokenResult refresh(RefreshCommand command) {
        String refreshTokenHash = tokenHasher.hash(command.refreshToken());

        UserSession session = sessionRepository
                .findValidByRefreshTokenHash(refreshTokenHash)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (!session.getDeviceId().equals(command.deviceId())) {
            throw new RuntimeException("Device mismatch");
        }

        // Thu hồi session cũ
        sessionRepository.revokeById(session.getId());

        // JPA: session.getUser() đã trả về đối tượng User nhờ @ManyToOne
        User user = session.getUser();

        String newAccessToken = tokenGenerator.generateAccessToken(user.getId(), user.getRole());
        String newRefreshToken = tokenGenerator.generateRefreshToken(user.getId());

        Instant now = timeProvider.now();
        UserSession newSession = new UserSession();
        newSession.setUser(user);
        newSession.setRefreshTokenHash(tokenHasher.hash(newRefreshToken));
        newSession.setDeviceId(session.getDeviceId());
        newSession.setDeviceName(session.getDeviceName());
        newSession.setIpAddress(session.getIpAddress());
        newSession.setExpiresAt(now.plus(30, ChronoUnit.DAYS));

        sessionRepository.save(newSession);

        return new TokenResult(newAccessToken, newRefreshToken);
    }

    @Transactional // Nên có transactional cho logout nếu có cập nhật DB
    @Override
    public void logout(LogoutCommand command) {
        String refreshTokenHash = tokenHasher.hash(command.refreshToken());
        UserSession session = sessionRepository
                .findValidByRefreshTokenHash(refreshTokenHash)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (!session.getDeviceId().equals(command.deviceId())) {
            throw new RuntimeException("Device mismatch");
        }

        sessionRepository.revokeById(session.getId());
    }
}

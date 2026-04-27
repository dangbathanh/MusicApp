package com.backend.MusicApp.auth.internal.service.impl;

import com.backend.MusicApp.auth.crypto.PasswordHasher;
import com.backend.MusicApp.auth.crypto.TokenHasher;
import com.backend.MusicApp.common.security.AppUserPrincipal;
import com.backend.MusicApp.auth.dto.request.LoginRequest;
import com.backend.MusicApp.auth.dto.request.LogoutRequest;
import com.backend.MusicApp.auth.dto.request.RefreshRequest;
import com.backend.MusicApp.auth.dto.request.RegisterRequest;
import com.backend.MusicApp.auth.dto.response.LoginResponse;
import com.backend.MusicApp.auth.dto.response.RefreshResponse;
import com.backend.MusicApp.auth.internal.entity.UserSession;
import com.backend.MusicApp.auth.internal.repository.UserSessionRepository;
import com.backend.MusicApp.auth.internal.service.AuthService;
import com.backend.MusicApp.auth.token.TokenGenerator;
import com.backend.MusicApp.common.config.JwtConfig;
import com.backend.MusicApp.common.exception.AppException;
import com.backend.MusicApp.common.exception.ErrorCode;
import com.backend.MusicApp.user.api.UserService;
import com.backend.MusicApp.user.api.dto.NewUserCommand;
import com.backend.MusicApp.user.api.dto.UserAccount;
import com.backend.MusicApp.user.api.dto.UserCredentials;
import com.fasterxml.uuid.Generators;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserSessionRepository sessionRepository;
    private final PasswordHasher passwordHasher;
    private final TokenHasher tokenHasher;
    private final TokenGenerator tokenGenerator;
    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest loginRequest, String ipAddress) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );
        AppUserPrincipal principal = (AppUserPrincipal) authentication.getPrincipal();
        UserCredentials creds = principal.credentials();

        UUID jti = Generators.timeBasedEpochGenerator().generate();
        Instant now = Instant.now();
        String accessToken = tokenGenerator.generateAccessToken(creds.id(), creds.role().name(), now);
        String refreshToken = tokenGenerator.generateRefreshToken(creds.id(), jti, now);

        createAndSaveSession(creds.id(), jti, refreshToken, loginRequest.deviceId(),
                loginRequest.deviceName(), ipAddress, now);

        UserAccount account = userService.findById(creds.id());
        return new LoginResponse(
                accessToken,
                refreshToken,
                account.username(),
                account.email()
        );
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        if (userService.existsByEmail(registerRequest.email())) {
            throw new AppException(ErrorCode.USER_EXIST);
        }
        String passwordHash = passwordHasher.hash(registerRequest.password());
        userService.register(new NewUserCommand(
                registerRequest.email(),
                registerRequest.username(),
                passwordHash
        ));
    }

    @Transactional
    @Override
    public RefreshResponse refresh(RefreshRequest refreshRequest, String ipAddress) {
        Claims claims = tokenGenerator.parseRefreshToken(refreshRequest.refreshToken());
        UUID jti = UUID.fromString(claims.getId());

        UserSession session = sessionRepository
                .findByJti(jti)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        if (!session.getDeviceId().equals(refreshRequest.deviceId())) {
            throw new AppException(ErrorCode.DEVICE_MISMATCH);
        }
        if (session.getRevokedAt() != null) {
            throw new AppException(ErrorCode.TOKEN_REUSE_DETECTION);
        }
        if (!Objects.equals(tokenHasher.hash(refreshRequest.refreshToken()), session.getRefreshTokenHash())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        session.setRevokedAt(Instant.now());
        sessionRepository.save(session);

        UserCredentials creds = userService.getCredentialsById(session.getUserId());

        UUID newJti = Generators.timeBasedEpochGenerator().generate();
        Instant now = Instant.now();
        String accessToken = tokenGenerator.generateAccessToken(creds.id(), creds.role().name(), now);
        String refreshToken = tokenGenerator.generateRefreshToken(creds.id(), newJti, now);

        createAndSaveSession(creds.id(), newJti, refreshToken, refreshRequest.deviceId(),
                session.getDeviceName(), ipAddress, now);
        return new RefreshResponse(accessToken, refreshToken);
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        Claims claims = tokenGenerator.parseRefreshToken(logoutRequest.refreshToken());
        UUID jti = UUID.fromString(claims.getId());
        UserSession session = sessionRepository.findByJti(jti)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));
        if (!session.getDeviceId().equals(logoutRequest.deviceId())) {
            throw new AppException(ErrorCode.DEVICE_MISMATCH);
        }
        session.setRevokedAt(Instant.now());
        sessionRepository.save(session);
    }

    private void createAndSaveSession(Long userId, UUID jti, String refreshToken, String deviceId,
                                      String deviceName, String ipAddress, Instant now) {
        Instant rtExpiry = now.plus(jwtConfig.getRefreshTokenDays(), ChronoUnit.DAYS);

        UserSession session = new UserSession();
        session.setJti(jti);
        session.setRefreshTokenHash(tokenHasher.hash(refreshToken));
        session.setDeviceId(deviceId);
        session.setDeviceName(deviceName);
        session.setIpAddress(ipAddress);
        session.setExpiresAt(rtExpiry);
        session.setIssuedAt(now);
        session.setCreatedAt(now);
        session.setUserId(userId);

        sessionRepository.save(session);
    }
}

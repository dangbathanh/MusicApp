package com.backend.MusicApp.auth.token;

import com.backend.MusicApp.common.exception.AppException;
import com.backend.MusicApp.common.exception.ErrorCode;
import com.backend.MusicApp.common.config.JwtConfig;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator implements TokenGenerator{
    private final JwtConfig jwtConfig;
    private final Key key;

    @Override
    public String generateAccessToken(Long userId, String role, Instant now) {
        Instant expiry = now.plus(jwtConfig.getAccessTokenMinutes(), ChronoUnit.MINUTES);
        String accessTokenJti = UUID.randomUUID().toString();
        return Jwts.builder()
                .subject(userId.toString())
                .id(accessTokenJti)
                .claim("role", role)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(key)
                .compact();

    }

    @Override
    public String generateRefreshToken(Long userId, UUID jti, Instant now) {
        Instant expiry = now.plus(jwtConfig.getRefreshTokenDays(), ChronoUnit.DAYS);
        return Jwts.builder()
                .subject(userId.toString())
                .id(jti.toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(key)
                .compact();
    }

    @Override
    public Claims parseRefreshToken(String refreshToken) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(refreshToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new AppException(ErrorCode.TOKEN_EXPIRED);
        } catch (JwtException e){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }


}

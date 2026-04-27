package com.backend.MusicApp.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenParser {

    private final Key key;

    public Claims parseAllClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public JwtClaims parse(String token){
        Claims claims = parseAllClaims(token);
        return new JwtClaims(
                Long.valueOf(claims.getSubject()),
                claims.get("role",String.class),
                UUID.fromString(claims.getId())
        );
    }

//    public JwtClaims parse(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        return new JwtClaims(
//                Long.valueOf(claims.getSubject()),
//                claims.get("role", String.class),
//                claims.getIssuedAt().toInstant(),
//                claims.getExpiration().toInstant()
//        );
//    }
}
package com.backend.MusicApp.common.security;

import com.backend.MusicApp.common.exception.AppException;
import com.backend.MusicApp.user.api.UserService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final Cache<Long, UserDetails> cacheById = Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofMinutes(2))
            .maximumSize(10_000)
            .recordStats()
            .build();

    @Override
    public UserDetails loadUserByUsername(String email) {
        try {
            return new AppUserPrincipal(userService.getCredentialsByEmail(email));
        } catch (AppException e) {
            throw new UsernameNotFoundException("User not found: " + email, e);
        }
    }

    public UserDetails loadUserById(Long userId) {
        return cacheById.get(userId, id -> {
            log.info("Cache MISS — querying DB for userId={}", id);
            try {
                return new AppUserPrincipal(userService.getCredentialsById(id));
            } catch (AppException e) {
                throw new UsernameNotFoundException("User not found: id=" + id, e);
            }
        });
    }

    public void evictById(Long userId) {
        cacheById.invalidate(userId);
    }
}

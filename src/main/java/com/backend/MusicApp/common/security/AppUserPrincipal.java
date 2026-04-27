package com.backend.MusicApp.common.security;

import com.backend.MusicApp.user.api.dto.UserCredentials;
import com.backend.MusicApp.user.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record AppUserPrincipal(UserCredentials credentials) implements UserDetails {

    @Override
    public String getUsername() {
        return credentials.email();
    }

    @Override
    public String getPassword() {
        return credentials.passwordHash();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + credentials.role().name()));
    }

    @Override
    public boolean isEnabled() {
        return credentials.status() == UserStatus.ACTIVE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return credentials.status() != UserStatus.LOCKED;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Long userId() {
        return credentials.id();
    }
}

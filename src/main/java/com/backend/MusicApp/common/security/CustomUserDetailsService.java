package com.backend.MusicApp.common.security;

import com.backend.MusicApp.common.exception.AppException;
import com.backend.MusicApp.user.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        try {
            return new AppUserPrincipal(userService.getCredentialsByEmail(email));
        } catch (AppException e) {
            throw new UsernameNotFoundException("User not found: " + email, e);
        }
    }

    public UserDetails loadUserById(Long userId) {
        try {
            return new AppUserPrincipal(userService.getCredentialsById(userId));
        } catch (AppException e) {
            throw new UsernameNotFoundException("User not found: id=" + userId, e);
        }
    }
}

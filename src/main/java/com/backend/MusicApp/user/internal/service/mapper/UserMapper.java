package com.backend.MusicApp.user.internal.service.mapper;

import com.backend.MusicApp.user.api.dto.UserAccount;
import com.backend.MusicApp.user.api.dto.UserCredentials;
import com.backend.MusicApp.user.internal.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserAccount toUserAccount(User u) {
        return new UserAccount(
                u.getId(),
                u.getEmail(),
                u.getUsername(),
                u.getRole(),
                u.getStatus(),
                u.getCreatedAt()
        );
    }

    public UserCredentials toUserCredentials(User u) {
        return new UserCredentials(
                u.getId(),
                u.getEmail(),
                u.getRole(),
                u.getStatus(),
                u.getPassword()
        );
    }
}

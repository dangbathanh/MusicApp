package com.backend.MusicApp.user.internal.service.impl;

import com.backend.MusicApp.common.exception.AppException;
import com.backend.MusicApp.common.exception.ErrorCode;
import com.backend.MusicApp.user.api.UserService;
import com.backend.MusicApp.user.api.dto.NewUserCommand;
import com.backend.MusicApp.user.api.dto.UserAccount;
import com.backend.MusicApp.user.api.dto.UserCredentials;
import com.backend.MusicApp.user.enums.UserRole;
import com.backend.MusicApp.user.enums.UserStatus;
import com.backend.MusicApp.user.internal.entity.User;
import com.backend.MusicApp.user.internal.repository.UserRepository;
import com.backend.MusicApp.user.internal.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserAccount findById(Long id) {
        return userMapper.toUserAccount(loadById(id));
    }

    @Override
    public UserAccount findByEmail(String email) {
        return userMapper.toUserAccount(loadByEmail(email));
    }

    @Override
    public UserCredentials getCredentialsByEmail(String email) {
        return userMapper.toUserCredentials(loadByEmail(email));
    }

    @Override
    public UserCredentials getCredentialsById(Long id) {
        return userMapper.toUserCredentials(loadById(id));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserAccount register(NewUserCommand cmd) {
        User user = new User();
        user.setEmail(cmd.email());
        user.setUsername(cmd.username());
        user.setPassword(cmd.passwordHash());
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        return userMapper.toUserAccount(userRepository.save(user));
    }

    private User loadById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    private User loadByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
}

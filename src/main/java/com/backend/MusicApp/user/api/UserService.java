package com.backend.MusicApp.user.api;

import com.backend.MusicApp.user.api.dto.NewUserCommand;
import com.backend.MusicApp.user.api.dto.UserAccount;
import com.backend.MusicApp.user.api.dto.UserCredentials;

public interface UserService {

    UserAccount findById(Long id);

    UserAccount findByEmail(String email);

    UserCredentials getCredentialsByEmail(String email);

    UserCredentials getCredentialsById(Long id);

    boolean existsByEmail(String email);

    UserAccount register(NewUserCommand cmd);
}

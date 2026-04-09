package com.backend.MusicApp.auth.service;

import com.backend.MusicApp.auth.dto.command.LoginCommand;
import com.backend.MusicApp.auth.dto.command.LogoutCommand;
import com.backend.MusicApp.auth.dto.command.RefreshCommand;
import com.backend.MusicApp.auth.dto.command.RegisterCommand;
import com.backend.MusicApp.auth.dto.result.LoginResult;
import com.backend.MusicApp.auth.dto.result.RegisterResult;
import com.backend.MusicApp.auth.dto.result.TokenResult;

public interface AuthService {
    LoginResult login(LoginCommand command);
    TokenResult refresh(RefreshCommand command);
    RegisterResult register(RegisterCommand command);
    void logout(LogoutCommand command);
}

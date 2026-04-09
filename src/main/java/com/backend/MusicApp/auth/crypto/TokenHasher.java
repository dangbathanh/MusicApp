package com.backend.MusicApp.auth.crypto;

public interface TokenHasher {
    String hash(String rawToken);
}

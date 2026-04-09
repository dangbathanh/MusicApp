package com.backend.MusicApp.auth.time;

import java.time.Instant;

public interface TimeProvider {
    Instant now();
}

package com.backend.MusicApp.common.util;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MediaUtils {

    private final String mediaBaseUrl;

    public MediaUtils(@Value("${app.media-base-url}") String mediaBaseUrl) {
        this.mediaBaseUrl = mediaBaseUrl;
    }

    @Named("toFullUrl")
    public String buildUrl(String path) {
        if (path == null || path.isBlank()) {
            return null;
        }
        if (path.startsWith("http")) {
            return path;
        }
        String sanitizedPath = path.startsWith("/") ? path : "/" + path;
        return mediaBaseUrl + sanitizedPath;
    }
}
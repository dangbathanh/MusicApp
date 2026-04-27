package com.backend.MusicApp.streaming.internal.repository.projection;

public interface SongFileProjection {
    String getUrl();
    Integer getQualityType();

    String getFormat();
    Long getFileSize();
    Integer getSampleRate();
    Integer getBitDepth();
    Integer getBitrate();
}
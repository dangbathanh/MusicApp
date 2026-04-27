package com.backend.MusicApp.streaming.internal.service.mapper;

import com.backend.MusicApp.common.util.MediaUtils;
import com.backend.MusicApp.streaming.api.dto.AudioResource;
import com.backend.MusicApp.streaming.internal.repository.projection.SongFileProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AudioResourceMapper {

    private final MediaUtils mediaUtils;

    public AudioResource toAudioResource(SongFileProjection p) {
        return new AudioResource(
                mediaUtils.buildUrl(p.getUrl()),
                p.getFormat(),
                p.getFileSize(),
                p.getSampleRate(),
                p.getBitDepth(),
                p.getBitrate(),
                p.getQualityType()
        );
    }
}

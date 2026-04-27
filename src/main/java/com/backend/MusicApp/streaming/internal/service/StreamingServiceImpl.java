package com.backend.MusicApp.streaming.internal.service;

import com.backend.MusicApp.common.exception.AppException;
import com.backend.MusicApp.common.exception.ErrorCode;
import com.backend.MusicApp.streaming.api.StreamingService;
import com.backend.MusicApp.streaming.api.dto.AudioResource;
import com.backend.MusicApp.streaming.internal.repository.SongFileRepository;
import com.backend.MusicApp.streaming.internal.service.mapper.AudioResourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamingServiceImpl implements StreamingService {

    private final SongFileRepository songFileRepository;
    private final AudioResourceMapper audioResourceMapper;

    @Override
    public AudioResource getAudioResource(Long songId, Integer quality) {
        return songFileRepository.findBySongIdAndQuality(songId, quality)
                .map(audioResourceMapper::toAudioResource)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
    }
}

package com.backend.MusicApp.catalog.internal.service.impl;

import com.backend.MusicApp.catalog.api.CatalogQueryService;
import com.backend.MusicApp.catalog.api.dto.SongMetadata;
import com.backend.MusicApp.catalog.internal.repository.SongRepository;
import com.backend.MusicApp.common.exception.AppException;
import com.backend.MusicApp.common.exception.ErrorCode;
import com.backend.MusicApp.common.util.JsonMapper;
import com.backend.MusicApp.common.util.MediaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogQueryServiceImpl implements CatalogQueryService {

    private final SongRepository songRepository;
    private final MediaUtils mediaUtils;
    private final JsonMapper jsonMapper;

    @Override
    public SongMetadata getSongMetadata(Long songId) {
        var p = songRepository.findSongMetadataById(songId)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
        return new SongMetadata(
                p.getId(),
                p.getTitle(),
                mediaUtils.buildUrl(p.getCoverUrl()),
                jsonMapper.convertToArtistList(p.getArtists()),
                p.getDurationSeconds(),
                p.getAlbumId()
        );
    }
}

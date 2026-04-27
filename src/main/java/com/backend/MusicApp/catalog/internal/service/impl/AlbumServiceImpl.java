package com.backend.MusicApp.catalog.internal.service.impl;

import com.backend.MusicApp.catalog.dto.response.AlbumDetailResponse;
import com.backend.MusicApp.catalog.object.ArtistObject;
import com.backend.MusicApp.catalog.internal.repository.AlbumRepository;
import com.backend.MusicApp.catalog.internal.repository.projection.AlbumProjection;
import com.backend.MusicApp.catalog.internal.repository.projection.AlbumSongItemProjection;
import com.backend.MusicApp.catalog.internal.service.AlbumService;
import com.backend.MusicApp.common.exception.AppException;
import com.backend.MusicApp.common.exception.ErrorCode;
import com.backend.MusicApp.common.util.JsonMapper;
import com.backend.MusicApp.common.util.MediaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SequencedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final MediaUtils mediaUtils;
    private final JsonMapper jsonMapper;

    @Override
    @Cacheable(value = "album_detail", key = "#albumId")
    public AlbumDetailResponse getAlbumDetail(Long albumId) {
        log.info("Cache miss cho Album ID: {}. Đang tính toán và map dữ liệu...", albumId);
        AlbumProjection album = albumRepository.findAlbumDetailById(albumId)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));
        List<AlbumSongItemProjection> songProjections = albumRepository.findAllSongsInAlbum(albumId);
        List<ArtistObject> albumArtists = jsonMapper.convertToArtistList(album.getArtists());

        SequencedMap<Integer, List<AlbumDetailResponse.SongItem>> discMap = songProjections.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getDiscNumber() != null ? p.getDiscNumber() : 1,
                        TreeMap::new,
                        Collectors.mapping(this::toSongItem, Collectors.toList())
                ));

        long totalDuration = songProjections.stream()
                .mapToLong(s -> s.getDurationSeconds() != null ? s.getDurationSeconds() : 0)
                .sum();

        return new AlbumDetailResponse(
                album.getId(),
                album.getTitle(),
                albumArtists,
                mediaUtils.buildUrl(album.getCoverUrl()),
                totalDuration,
                songProjections.size(),
                album.getHighestQualityType() != null ? album.getHighestQualityType() : 0,
                album.getReleaseYear(),
                discMap.sequencedEntrySet().stream()
                        .map(e -> new AlbumDetailResponse.DiscObject(e.getKey(), e.getValue()))
                        .toList()
        );
    }

    private AlbumDetailResponse.SongItem toSongItem(AlbumSongItemProjection p) {
        return new AlbumDetailResponse.SongItem(
                p.getId(),
                p.getTrackNumber(),
                p.getTitle(),
                jsonMapper.convertToArtistList(p.getArtists()),
                p.getDurationSeconds()
        );
    }
}

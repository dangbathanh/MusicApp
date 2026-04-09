package com.backend.MusicApp.catalog.service.mapper;

import com.backend.MusicApp.catalog.dto.songPlay.ArtistObject;
import com.backend.MusicApp.catalog.dto.songPlay.response.SongPlayResponse;
import com.backend.MusicApp.catalog.repository.dto.SongFileProjection;
import com.backend.MusicApp.catalog.repository.dto.SongProjection;
import com.backend.MusicApp.common.util.MediaUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class SongDataMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mapping(target = "id", source = "metadata.id")
    @Mapping(target = "title", source = "metadata.title")
    @Mapping(target = "coverUrl", source = "metadata.coverUrl", qualifiedByName = "toFullUrl")
    @Mapping(target = "artists", source = "metadata.artistsJson", qualifiedByName = "jsonToArtistList")
    @Mapping(target = "durationSeconds", source = "metadata.durationSeconds")
    @Mapping(target = "albumId", source = "metadata.albumId")
    @Mapping(target = "url", source = "file.url", qualifiedByName = "toFullUrl") // Build URL cho file nhạc
    @Mapping(target = "format", source = "file.format")
    @Mapping(target = "fileSize", source = "file.fileSize")
    @Mapping(target = "sampleRate", source = "file.sampleRate")
    @Mapping(target = "bitDepth", source = "file.bitDepth")
    @Mapping(target = "bitrate", source = "file.bitrate")
    public abstract SongPlayResponse toSongPlayResponse(
            SongProjection metadata,
            SongFileProjection file,
            @Context MediaUtils mediaUtils
    );

    @Named("toFullUrl")
    protected String toFullUrl(String path, @Context MediaUtils mediaUtils) {
        return mediaUtils.buildUrl(path);
    }

    @Named("jsonToArtistList")
    protected List<ArtistObject> jsonToArtistList(String json) {
        try {
            if (json == null || json.isBlank()) return List.of();
            return objectMapper.readValue(json, new TypeReference<List<ArtistObject>>() {});
        } catch (Exception e) {
            return List.of();
        }
    }
}
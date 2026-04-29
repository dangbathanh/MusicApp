package com.backend.MusicApp.catalog.internal.service.mapper;

import com.backend.MusicApp.catalog.dto.AlbumItem;
import com.backend.MusicApp.catalog.dto.ArtistItem;
import com.backend.MusicApp.catalog.dto.SongItem;
import com.backend.MusicApp.catalog.internal.repository.projection.AlbumCardProjection;
import com.backend.MusicApp.catalog.internal.repository.projection.ArtistCardProjection;
import com.backend.MusicApp.catalog.internal.repository.projection.SongCardProjection;
import com.backend.MusicApp.common.util.JsonMapper;
import com.backend.MusicApp.common.util.MediaUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MediaUtils.class, JsonMapper.class})
public interface CatalogItemMapper {

    @Mapping(target = "imageUrl", source = "coverUrl", qualifiedByName = "toFullUrl")
    @Mapping(target = "artists", source = "artists", qualifiedByName = "convertToArtistList")
    SongItem toSongItem(SongCardProjection projection);

    @Mapping(target = "imageUrl", source = "coverUrl", qualifiedByName = "toFullUrl")
    @Mapping(target = "artists", source = "artists", qualifiedByName = "convertToArtistList")
    AlbumItem toAlbumItem(AlbumCardProjection projection);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "imageUrl", source = "avatarUrl", qualifiedByName = "toFullUrl")
    ArtistItem toArtistItem(ArtistCardProjection projection);

    List<SongItem> toSongItems(List<SongCardProjection> projections);

    List<AlbumItem> toAlbumItems(List<AlbumCardProjection> projections);

    List<ArtistItem> toArtistItems(List<ArtistCardProjection> projections);
}

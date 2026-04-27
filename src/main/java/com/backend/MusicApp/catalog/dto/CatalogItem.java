package com.backend.MusicApp.catalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "itemType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SongItem.class,   name = "SONG"),
        @JsonSubTypes.Type(value = AlbumItem.class,  name = "ALBUM"),
        @JsonSubTypes.Type(value = ArtistItem.class, name = "ARTIST")
})
public sealed interface CatalogItem permits SongItem, AlbumItem, ArtistItem {
    Long id();
    String title();
    String imageUrl();
}







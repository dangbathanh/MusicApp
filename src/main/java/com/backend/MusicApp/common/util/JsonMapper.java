package com.backend.MusicApp.common.util;

import com.backend.MusicApp.catalog.object.ArtistObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonMapper {
    private final ObjectMapper commonObjectMapper;

    @Named("convertToJson")
    public String convertToJson(Object object) {
        try {
            return object == null ? "[]" : commonObjectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error converting to JSON: ", e);
            return "[]";
        }
    }

    public <T> List<T> convertToList(String json, TypeReference<List<T>> typeReference) {
        try {
            if (json == null || json.isBlank()) {
                return Collections.emptyList();
            }
            return commonObjectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.warn("Failed to parse JSON list: {}. Error: {}", json, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Named("convertToArtistList")
    public List<ArtistObject> convertToArtistList(String json) {
        return convertToList(json, new TypeReference<List<ArtistObject>>() {});
    }
}

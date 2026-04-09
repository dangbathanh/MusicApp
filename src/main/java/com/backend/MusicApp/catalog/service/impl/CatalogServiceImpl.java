package com.backend.MusicApp.catalog.service.impl;

import com.backend.MusicApp.catalog.dto.home.HomeResponse;
import com.backend.MusicApp.catalog.repository.SongRepository;
import com.backend.MusicApp.catalog.repository.dto.SongCardProjection;
import com.backend.MusicApp.catalog.service.CatalogService;
import com.backend.MusicApp.common.util.MediaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final SongRepository songRepository;
    private final MediaUtils mediaUtils;

    @Override
    public HomeResponse getHomePageData() {
        // 1. Lấy dữ liệu bài hát mới nhất từ Repository
        var latestSongs = songRepository.findTopNewest(10);

        // 2. Chuyển đổi từ Projection sang HomeItem DTO
        var items = latestSongs.stream()
                .map(this::mapToHomeItem).toList();

        // 3. Đóng gói vào Section "Mới phát hành"
        var newReleaseSection = new HomeResponse.HomeSection(
                "Mới phát hành",
                "GRID",
                items
        );

        return new HomeResponse(List.of(newReleaseSection));
    }

    private HomeResponse.HomeItem mapToHomeItem(SongCardProjection s) {
        return new HomeResponse.HomeItem(
                s.id(),
                s.title(),
                s.artistName(),
                mediaUtils.buildUrl(s.coverUrl()),
                "SONG"
        );
    }
}

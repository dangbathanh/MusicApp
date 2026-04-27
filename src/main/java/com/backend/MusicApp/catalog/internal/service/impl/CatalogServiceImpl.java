package com.backend.MusicApp.catalog.internal.service.impl;

import com.backend.MusicApp.catalog.dto.CatalogItem;
import com.backend.MusicApp.catalog.dto.response.HomeResponse;
import com.backend.MusicApp.catalog.internal.repository.AlbumRepository;
import com.backend.MusicApp.catalog.internal.repository.SongRepository;
import com.backend.MusicApp.catalog.internal.service.CatalogService;
import com.backend.MusicApp.catalog.internal.service.mapper.CatalogItemMapper;
import com.backend.MusicApp.common.exception.AppException;
import com.backend.MusicApp.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatalogServiceImpl implements CatalogService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    @Qualifier("ioExecutor")
    private final Executor ioExecutor;
    private final CatalogItemMapper catalogItemMapper;

    @Override
    @Cacheable(value = "home_catalog")
    public HomeResponse getHomePageData() {
        log.info("Cache miss! Đang tính toán dữ liệu trang Home bằng CompletableFuture...");
        List<Supplier<HomeResponse.HomeSection>> tasks = List.of(
                this::getNewReleaseSongs,
                this::getNewReleaseAlbums,
                this::getRandomAlbums
        );

        List<CompletableFuture<HomeResponse.HomeSection>> futures = tasks.stream()
                .map(task -> CompletableFuture.supplyAsync(task, ioExecutor)
                        .exceptionally(ex -> {
                            log.error("Lỗi khi tải một mục trên Home: {}", ex.getMessage());
                            return null;
                        }))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        List<HomeResponse.HomeSection> sections = futures.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .toList();

        if (sections.isEmpty()) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }

        return new HomeResponse(sections);
    }

    private HomeResponse.HomeSection getNewReleaseSongs() {
        var latestSongs = songRepository.findTopNewest(10);
        List<CatalogItem> items = latestSongs.stream()
                .<CatalogItem>map(catalogItemMapper::toSongItem)
                .toList();
        return new HomeResponse.HomeSection(
                "Mới phát hành",
                "GRID",
                items
        );
    }

    private HomeResponse.HomeSection getNewReleaseAlbums() {
        var latestAlbums = albumRepository.findTopNewest(10);
        List<CatalogItem> items = latestAlbums.stream()
                .<CatalogItem>map(catalogItemMapper::toAlbumItem)
                .toList();
        return new HomeResponse.HomeSection(
                "Album mới",
                "GRID",
                items
        );
    }

    private HomeResponse.HomeSection getRandomAlbums() {
        var randomAlbums = albumRepository.findRandomAlbums(10);
        List<CatalogItem> items = randomAlbums.stream()
                .<CatalogItem>map(catalogItemMapper::toAlbumItem)
                .toList();
        return new HomeResponse.HomeSection(
                "Có thể bạn thích",
                "GRID",
                items
        );
    }
}

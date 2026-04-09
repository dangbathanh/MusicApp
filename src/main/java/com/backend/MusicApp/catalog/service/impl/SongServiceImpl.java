package com.backend.MusicApp.catalog.service.impl;

import com.backend.MusicApp.catalog.dto.songPlay.response.SongPlayResponse;
import com.backend.MusicApp.catalog.repository.SongFileRepository;
import com.backend.MusicApp.catalog.repository.dto.SongFileProjection;
import com.backend.MusicApp.catalog.service.SongService;
import com.backend.MusicApp.catalog.repository.SongRepository;
import com.backend.MusicApp.common.exception.ResourceNotFoundException;
import com.backend.MusicApp.common.util.MediaUtils;
import com.backend.MusicApp.catalog.service.mapper.SongDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongFileRepository songFileRepository;
    private final MediaUtils mediaUtils;
    private final SongDataMapper songDataMapper;


    @Override
    public SongPlayResponse getSongToPlay(Long id, Integer quality) {
        var metadata = songRepository.findSongMetadataById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bài hát"));

        var allFiles = songFileRepository.findAllBySongId(id);
        if (allFiles.isEmpty()) {
            throw new ResourceNotFoundException("Bài hát này chưa có file nhạc");
        }

        SongFileProjection selectedFile = allFiles.stream()
                // Chỉ lấy những file có chất lượng <= mức yêu cầu
                .filter(f -> f.qualityType() <= quality)
                .findFirst()
                // Nếu không tìm thấy file nào thỏa mãn (ví dụ yêu cầu 1 nhưng chỉ có 2, 3)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Không tìm thấy chất lượng nhạc phù hợp với yêu cầu của bạn"));


        return songDataMapper.toSongPlayResponse(metadata, selectedFile, mediaUtils);
    }
}
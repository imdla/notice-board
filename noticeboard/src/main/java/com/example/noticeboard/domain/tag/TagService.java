package com.example.noticeboard.domain.tag;

import com.example.noticeboard.domain.tag.dto.TagRequestDto;
import com.example.noticeboard.domain.tag.dto.TagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {
    private TagRepository tagRepository;

    @Transactional
    public type addTag(TagRequestDto requestDto) {
        return TagResponseDto.from(
                tagRepository.save(requestDto.toEntity());
        );
    }
}

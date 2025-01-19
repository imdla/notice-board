package com.example.noticeboard.domain.tag;

import com.example.noticeboard.domain.tag.dto.TagRequestDto;
import com.example.noticeboard.domain.tag.dto.TagResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {
    private TagRepository tagRepository;

    @Transactional
    public TagResponseDto addTag(TagRequestDto requestDto) {
        return TagResponseDto.from(
                tagRepository.save(requestDto.toEntity())
        );
    }

    public List<TagResponseDto> getTags() {
        return tagRepository.findAll().stream()
                .map(TagResponseDto::from)
                .toList();
    }
}

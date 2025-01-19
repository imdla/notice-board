package com.example.noticeboard.domain.tag;

import com.example.noticeboard.domain.tag.dto.TagRequestDto;
import com.example.noticeboard.domain.tag.dto.TagResponseDto;
import com.example.noticeboard.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private TagService tagService;

    @PostMapping
    public ResponseEntity<ApiResponse<TagResponseDto>> addTag(@Valid @RequestBody TagRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(tagService.addTag(requestDto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TagResponseDto>>> getTags() {
        return ResponseEntity.ok(ApiResponse.ok(
                tagService.getTags()
        ));
    }
}

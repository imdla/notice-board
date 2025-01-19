package com.example.noticeboard.domain.post;

import com.example.noticeboard.domain.post.dto.PostRequestDto;
import com.example.noticeboard.domain.post.dto.PostResponseDto;
import com.example.noticeboard.domain.user.entity.User;
import com.example.noticeboard.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponseDto>> addPost(
            @Valid @RequestPart(value = "data") PostRequestDto requestDto,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @AuthenticationPrincipal User user
            ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        postService.addPost(requestDto, image, user)
                ));
    }
}

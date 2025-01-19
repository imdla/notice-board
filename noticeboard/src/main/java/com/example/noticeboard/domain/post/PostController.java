package com.example.noticeboard.domain.post;

import com.example.noticeboard.domain.post.dto.request.PostRequestDto;
import com.example.noticeboard.domain.post.dto.response.PostListResponseDto;
import com.example.noticeboard.domain.post.dto.response.PostResponseDto;
import com.example.noticeboard.domain.user.entity.User;
import com.example.noticeboard.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    // 게시글 작성 (image, tag)
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

    // 게시글 조회 (pagination)
    @GetMapping
    public ResponseEntity<ApiResponse<PostListResponseDto>> getPosts(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.getPosts(pageable)
        ));
    }

    // 태그별 게시글 조회
    @GetMapping("/tags")
    public type getPostsByTags() {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.getPostsByTags()
        ));
    }
}

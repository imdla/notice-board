package com.example.noticeboard.domain.post;

import com.example.noticeboard.domain.post.dto.request.PostRequestDto;
import com.example.noticeboard.domain.post.dto.response.PostListResponseDto;
import com.example.noticeboard.domain.post.dto.response.PostResponseDto;
import com.example.noticeboard.domain.post.dto.response.PostWithTagResponseDto;
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

import java.util.List;


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

    // 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.getPostById(postId)
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
    public ResponseEntity<ApiResponse<List<PostWithTagResponseDto>>> getPostsByTag(
            @RequestParam String tag
    ) {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.getPostsByTag(tag)
        ));
    }

    // 게시글 수정
    @PostMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(
            @RequestParam Long postId,
            @Valid @RequestPart(value = "data") PostRequestDto requestDto,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(ApiResponse.ok(
                        postService.updatePost(postId, requestDto, image, user)
                ));
    }

    // 게시글 삭제
    @DeleteMapping("/postId")
    public void deletePost(
            @RequestParam Long postId,
            @AuthenticationPrincipal User user
    ) {
        ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.ok(
                        postService.deletePost(postId, user)
                ));
    }
}

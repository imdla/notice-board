package com.example.noticeboard.domain.comment;

import com.example.noticeboard.domain.comment.dto.CommentRequestDto;
import com.example.noticeboard.domain.comment.dto.CommentResponseDto;
import com.example.noticeboard.domain.user.entity.User;
import com.example.noticeboard.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ApiResponse<CommentResponseDto>> addComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal User author
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        commentService.addComment(postId, requestDto, author)
                ));
    }

    @PutMapping("/{commentId}")
    public type updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto,
            @AuthenticationPrincipal User author
    ) {
        return ResponseEntity.ok(ApiResponse.ok(
                commentService.updateComment(postId, commentId, requestDto)
        ));
    }
}

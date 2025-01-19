package com.example.noticeboard.domain.comment.dto;

import com.example.noticeboard.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponseDto {
    private final Long id;
    private final String content;
    private LocalDateTime createdAt;
    private String author;

    public static CommentResponseDto from(Comment entity) {
        return CommentResponseDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .author(entity.getAuthor().getUsername())
                .build();
    }
}

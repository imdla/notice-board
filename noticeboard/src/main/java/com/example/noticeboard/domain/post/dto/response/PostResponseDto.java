package com.example.noticeboard.domain.post.dto.response;

import com.example.noticeboard.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String imageUrl;
    private final List<String> tags;

    public static PostResponseDto from(Post entity) {
        return PostResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor().getUsername())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .imageUrl(entity.getImageUrl())
                .tags(entity.getPostTags().stream().map(
                        postTag -> postTag.getTag().getName()
                ).toList())
                .build();
    }
}

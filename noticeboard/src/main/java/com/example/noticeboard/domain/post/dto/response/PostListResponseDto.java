package com.example.noticeboard.domain.post.dto.response;

import com.example.noticeboard.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PostListResponseDto {
    private final List<PostItemResponseDto> posts;
    private final long totalPages;
    private final boolean hasNext;
    private final boolean hasPrevious;

    public static PostListResponseDto from(Page<Post> posts) {
        return PostListResponseDto.builder()
                .posts(
                        posts.getContent().stream().map(
                                PostItemResponseDto::from
                        ).toList()
                )
                .totalPages(posts.getTotalPages())
                .hasNext(posts.hasNext())
                .hasPrevious(posts.hasPrevious())
                .build();
    }

    @Getter
    @Builder
    public class PostItemResponseDto {
        private final Long id;
        private final String title;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public static PostItemResponseDto from(Post entity) {
            return PostItemResponseDto.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .createdAt(entity.getCreatedAt())
                    .updatedAt(entity.getUpdatedAt())
                    .build();
        }
    }
}

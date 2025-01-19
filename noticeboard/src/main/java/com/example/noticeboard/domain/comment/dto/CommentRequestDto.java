package com.example.noticeboard.domain.comment.dto;

import com.example.noticeboard.domain.comment.Comment;
import com.example.noticeboard.domain.post.entity.Post;
import com.example.noticeboard.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    @NotBlank(message = "내용은 필수 입력입니다.")
    private String content;

    public Comment toEntity(Post post, User author) {
        return Comment.builder()
                .content(this.content)
                .post(post)
                .author(author)
                .build();
    }
}

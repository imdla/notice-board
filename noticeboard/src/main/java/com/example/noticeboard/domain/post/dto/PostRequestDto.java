package com.example.noticeboard.domain.post.dto;

import com.example.noticeboard.domain.post.entity.Post;
import com.example.noticeboard.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    @NotBlank(message = "제목은 필수 입력입니다.")
    @Length(min = 2, max = 20, message = "제목은 2글자 이상 20글자 미만 입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력입니다.")
    @Length(min = 5, message = "내용은 5글자 이상 입니다.")
    private String content;

    public Post toEntity(User author) {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .author(author)
                .build();
    }
}

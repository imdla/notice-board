package com.example.noticeboard.domain.tag.dto;

import com.example.noticeboard.domain.tag.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TagRequestDto {
    @NotBlank(message = "태그명은 필수 입력입니다.")
    private String name;

    public Tag toEntity() {
        return Tag.builder()
                .name(this.name)
                .build();
    }
}

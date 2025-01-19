package com.example.noticeboard.domain.user.dto;

import com.example.noticeboard.domain.user.entity.Role;
import com.example.noticeboard.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SignupRequestDto {
    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Length(min = 2, message = "최소 2글자 이상 입력입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "비밀번호는 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력입니다")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .username(this.getUsername())
                .password(encodedPassword)
                .email(this.getEmail())
                .role(Role.ROLE_USER)
                .build();
    }
}

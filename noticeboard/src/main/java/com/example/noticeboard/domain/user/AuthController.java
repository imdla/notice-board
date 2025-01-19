package com.example.noticeboard.domain.user;

import com.example.noticeboard.domain.user.dto.request.LoginRequestDto;
import com.example.noticeboard.domain.user.dto.request.SignupRequestDto;
import com.example.noticeboard.domain.user.dto.response.SignupResponseDto;
import com.example.noticeboard.domain.user.dto.response.TokenResponseDto;
import com.example.noticeboard.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponseDto>> signup(
            @Valid @RequestBody SignupRequestDto requestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        authService.signup(requestDto)
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponseDto>> login(
            @Valid @RequestBody LoginRequestDto requestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        authService.login(requestDto)
                ));
    }
}

package com.example.noticeboard.domain.post;

import com.example.noticeboard.domain.post.dto.PostRequestDto;
import com.example.noticeboard.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public type addPost(PostRequestDto requestDto, MultipartFile image, User user) {
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = fileService.saveFile(image);
        }
    }
}

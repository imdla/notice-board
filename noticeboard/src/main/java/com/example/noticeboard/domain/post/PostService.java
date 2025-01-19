package com.example.noticeboard.domain.post;

import com.example.noticeboard.domain.post.dto.PostRequestDto;
import com.example.noticeboard.domain.post.entity.Post;
import com.example.noticeboard.domain.user.entity.User;
import com.example.noticeboard.global.common.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final FileService fileService;

    @Transactional
    public type addPost(PostRequestDto requestDto, MultipartFile image, User user) {
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = fileService.saveFile(image);
        }

        Post post = requestDto.toEntity(user);
        post.addImageUrl(imageUrl);

        return PostResponseDto.from(postRepository.save(post));
    }
}

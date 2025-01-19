package com.example.noticeboard.domain.post;

import com.example.noticeboard.domain.post.dto.request.PostRequestDto;
import com.example.noticeboard.domain.post.dto.response.PostListResponseDto;
import com.example.noticeboard.domain.post.dto.response.PostResponseDto;
import com.example.noticeboard.domain.post.dto.response.PostWithTagResponseDto;
import com.example.noticeboard.domain.post.entity.Post;
import com.example.noticeboard.domain.post.entity.PostTag;
import com.example.noticeboard.domain.tag.Tag;
import com.example.noticeboard.domain.tag.TagRepository;
import com.example.noticeboard.domain.user.entity.User;
import com.example.noticeboard.global.common.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final FileService fileService;
    private final TagRepository tagRepository;

    // 게시글 작성 (image, tag)
    @Transactional
    public PostResponseDto addPost(PostRequestDto requestDto, MultipartFile image, User user) {
        // 이미지 설정
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = fileService.saveFile(image);
        }

        Post post = requestDto.toEntity(user);
        post.addImageUrl(imageUrl);

        // tag 설정
        List<String> tagNames = requestDto.getTags();
        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName).orElseGet(() -> {
                Tag newTag = new Tag(tagName);
                return tagRepository.save(newTag);
            });
            PostTag postTag = new PostTag(post, tag);
            post.getPostTags().add(postTag);
        }

        return PostResponseDto.from(postRepository.save(post));
    }

    // 게시글 조회 (pagination)
    public PostListResponseDto getPosts(Pageable pageable) {
        return PostListResponseDto.from(postRepository.findAll(pageable));
    }

    // 태그별 게시글 조회
    public List<PostWithTagResponseDto> getPostsByTag(String tag) {
        return postRepository.findAllByTagName(tag).stream()
                .map(PostWithTagResponseDto::from).toList();
    }
}

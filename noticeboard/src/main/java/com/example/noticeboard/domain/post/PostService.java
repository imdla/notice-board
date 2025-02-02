package com.example.noticeboard.domain.post;

import com.example.noticeboard.domain.comment.CommentRepository;
import com.example.noticeboard.domain.comment.dto.CommentResponseDto;
import com.example.noticeboard.domain.post.dto.request.PostRequestDto;
import com.example.noticeboard.domain.post.dto.response.PostListResponseDto;
import com.example.noticeboard.domain.post.dto.response.PostResponseDto;
import com.example.noticeboard.domain.post.dto.response.PostWithTagResponseDto;
import com.example.noticeboard.domain.post.entity.Post;
import com.example.noticeboard.domain.post.entity.PostTag;
import com.example.noticeboard.domain.tag.Tag;
import com.example.noticeboard.domain.tag.TagRepository;
import com.example.noticeboard.domain.user.entity.Role;
import com.example.noticeboard.domain.user.entity.User;
import com.example.noticeboard.global.common.FileService;
import com.example.noticeboard.global.exception.ResourceNotFoundException;
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
    private final CommentRepository commentRepository;

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

    // 게시글 조회
    public PostResponseDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(ResourceNotFoundException::new);
        return PostResponseDto.from(post);
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

    // 게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto, MultipartFile image, User user) {
        Post post = postRepository.findById(postId).orElseThrow(ResourceNotFoundException::new);

        // 작성자 확인
        if (!post.getAuthor().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }

        post.update(requestDto);

        // 이미지 설정
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = fileService.saveFile(image);
        }
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

        return PostResponseDto.from(post);
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(ResourceNotFoundException::new);
        if (!(post.getAuthor().getUsername().equals(user.getUsername())) || !(user.getRole() == Role.ROLE_ADMIN)) {
            throw new IllegalArgumentException("게시글을 삭제할 수 없습니다.");
        }

        postRepository.delete(post);
    }

    // 게시글의 댓글 조회
    public List<CommentResponseDto> getComments(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(CommentResponseDto::from).toList();
    }
}

package com.example.noticeboard.domain.comment;

import com.example.noticeboard.domain.comment.dto.CommentRequestDto;
import com.example.noticeboard.domain.comment.dto.CommentResponseDto;
import com.example.noticeboard.domain.post.PostRepository;
import com.example.noticeboard.domain.post.entity.Post;
import com.example.noticeboard.domain.user.entity.User;
import com.example.noticeboard.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto addComment(Long postId, CommentRequestDto requestDto, User author) {
        Post post = postRepository.findById(postId)
                .orElseThrow(ResourceNotFoundException::new);

        Comment comment = requestDto.toEntity(post, author);
        return CommentResponseDto.from(comment);
    }

    @Transactional
    public type updateComment(Long postId, Long commentId, CommentRequestDto requestDto, User author) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(ResourceNotFoundException::new);

        if (!comment.getAuthor().getUsername().equals(author.getUsername())) {
            throw new IllegalArgumentException("댓글 작성자가 아닙니다.");
        }

        return CommentResponseDto.from(comment.update(requestDto));
    }
}

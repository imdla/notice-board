package com.example.noticeboard.domain.comment;

import com.example.noticeboard.domain.comment.dto.CommentRequestDto;
import com.example.noticeboard.domain.post.entity.Post;
import com.example.noticeboard.domain.user.entity.User;
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
    public type addComment(Long postId, CommentRequestDto requestDto, User author) {
        Post post =
    }
}

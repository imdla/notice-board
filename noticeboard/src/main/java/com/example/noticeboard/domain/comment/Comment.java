package com.example.noticeboard.domain.comment;

import com.example.noticeboard.domain.comment.dto.CommentRequestDto;
import com.example.noticeboard.domain.post.entity.Post;
import com.example.noticeboard.domain.user.entity.User;
import com.example.noticeboard.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Builder
    public Comment (String content, Post post, User author) {
        this.content = content;
        this.post = post;
        this.author = author;
    }

    public Comment update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
        return this;
    }
}

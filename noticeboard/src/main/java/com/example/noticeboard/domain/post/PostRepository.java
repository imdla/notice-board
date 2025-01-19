package com.example.noticeboard.domain.post;

import com.example.noticeboard.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

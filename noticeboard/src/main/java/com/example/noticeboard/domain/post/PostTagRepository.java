package com.example.noticeboard.domain.post;

import com.example.noticeboard.domain.post.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {
}

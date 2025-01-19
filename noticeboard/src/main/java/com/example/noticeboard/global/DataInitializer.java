package com.example.noticeboard.global;


import com.example.noticeboard.domain.comment.Comment;
import com.example.noticeboard.domain.comment.CommentRepository;
import com.example.noticeboard.domain.post.PostRepository;
import com.example.noticeboard.domain.post.PostTagRepository;
import com.example.noticeboard.domain.post.entity.Post;
import com.example.noticeboard.domain.post.entity.PostTag;
import com.example.noticeboard.domain.tag.Tag;
import com.example.noticeboard.domain.tag.TagRepository;
import com.example.noticeboard.domain.user.AuthService;
import com.example.noticeboard.domain.user.dto.request.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PostRepository postRepository;
    private final AuthService authService;
    private final PostTagRepository postTagRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;


    @Override
    public void run(String... args) throws Exception {
        initUser();
        initPost();
    }

    public void initUser() {

        SignupRequestDto requestDto = SignupRequestDto.builder()
                .username("haha")
                .password("haha123!")
                .email("haha@example.com")
                .build();

        authService.signup(requestDto);
    }


    @Transactional
    public void initPost(){
        if (postRepository.count() == 0){

            for (int i=1; i<=3; i++) {
                Post post = Post.builder()
                        .title("title" + i)
                        .content("content" + i)
                        .build();
                postRepository.save(post);

                for (int j=1; j<=2; j++){
                    Tag tag = Tag.builder().name("tag" + i + j).build();
                    tagRepository.save(tag);

                    PostTag postTag = new PostTag();
                    postTag.addPost(post);
                    postTag.addTag(tag);

                    postTagRepository.save(postTag);

                    Comment comment = Comment.builder()
                            .content("comment" + i + j)
                            .post(post).build();
                    commentRepository.save(comment);
                }
            }
        }
    }
}

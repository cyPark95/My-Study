package pcy.study.sns.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.sns.domain.post.dto.PostCommand;
import pcy.study.sns.domain.post.entity.Post;
import pcy.study.sns.domain.post.repository.PostRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PostWriteService {

    private final PostRepository postRepository;

    public Long register(Long memberId, PostCommand command) {
        var post = Post.builder()
                .memberId(memberId)
                .contents(command.contents())
                .build();

        return postRepository.save(post).getId();
    }

    public void likePostByOptimisticLock(Long postId) {
        var post = postRepository.findById(postId)
                .orElseThrow();
        post.incrementLickCount();
        postRepository.save(post);
    }
}

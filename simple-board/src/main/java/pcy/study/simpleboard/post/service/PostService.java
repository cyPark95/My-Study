package pcy.study.simpleboard.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.simpleboard.post.db.Post;
import pcy.study.simpleboard.post.db.PostRepository;
import pcy.study.simpleboard.post.model.PostCreateRequest;
import pcy.study.simpleboard.post.model.PostDeleteRequest;
import pcy.study.simpleboard.post.model.PostGetRequest;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostCreateRequest postCreateRequest) {
        log.info("Save Post Info = {}", postCreateRequest);
        var entity = postCreateRequest.toPost();
        postRepository.save(entity);
        return entity.getId();
    }

    public Post findPost(PostGetRequest postGetRequest) {
        var post = findPostById(postGetRequest.id());
        log.info("Find Post = {}", post);

        matchesPassword(post, postGetRequest.password());
        return post;
    }

    private Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
    }

    public List<Post> findPostAll() {
        return postRepository.findAll();
    }

    @Transactional
    public void delete(PostDeleteRequest postDeleteRequest) {
        var post = findPostById(postDeleteRequest.id());
        log.info("Delete Post = {}", post);

        matchesPassword(post, postDeleteRequest.password());
        postRepository.delete(post);
    }

    private void matchesPassword(Post post, String password) {
        if (!post.getPassword().equals(password)) {
            throw new IllegalArgumentException("잘못된 패스워드 입니다.");
        }
    }
}

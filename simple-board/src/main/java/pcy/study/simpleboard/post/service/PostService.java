package pcy.study.simpleboard.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.simpleboard.board.service.BoardService;
import pcy.study.simpleboard.post.db.Post;
import pcy.study.simpleboard.post.db.PostRepository;
import pcy.study.simpleboard.post.model.PostCreateRequest;
import pcy.study.simpleboard.post.model.PostDeleteRequest;
import pcy.study.simpleboard.post.model.PostDetailsRequest;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final BoardService boardService;

    @Transactional
    public Long save(PostCreateRequest postCreateRequest) {
        log.info("Save Post Info = {}", postCreateRequest);
        var entity = postCreateRequest.toPost();
        var board = boardService.findBoardById(postCreateRequest.boardId());

        entity.registerBoard(board);
        postRepository.save(entity);
        return entity.getId();
    }

    public Post findPost(PostDetailsRequest postDetailsRequest) {
        var post = findPostByIdWithReply(postDetailsRequest);
        log.info("Find Post = {}", post);

        matchesPassword(post, postDetailsRequest.password());
        return post;
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

    public Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("[%d] 존재하지 않는 게시글입니다.", id)));
    }

    private Post findPostByIdWithReply(PostDetailsRequest postDetailsRequest) {
        return postRepository.findByIdFetchReply(postDetailsRequest.id())
                .orElseThrow(() -> new IllegalArgumentException(String.format("[%d] 존재하지 않는 게시글입니다.", postDetailsRequest.id())));
    }

    private void matchesPassword(Post post, String password) {
        if (!post.getPassword().equals(password)) {
            throw new IllegalArgumentException("잘못된 패스워드 입니다.");
        }
    }
}

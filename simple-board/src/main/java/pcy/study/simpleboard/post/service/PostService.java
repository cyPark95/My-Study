package pcy.study.simpleboard.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.simpleboard.post.db.PostRepository;
import pcy.study.simpleboard.post.model.PostCreateRequest;

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
}

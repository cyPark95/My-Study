package pcy.study.simpleboard.reply.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.simpleboard.post.service.PostService;
import pcy.study.simpleboard.reply.db.ReplyRepository;
import pcy.study.simpleboard.reply.model.ReplyCreateRequest;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final PostService postService;

    @Transactional
    public Long save(ReplyCreateRequest replyCreateRequest) {
        log.info("Save Reply Info = {}", replyCreateRequest);
        var entity = replyCreateRequest.toReply();
        var post = postService.findPostById(replyCreateRequest.postId());

        entity.registerPost(post);
        replyRepository.save(entity);
        return entity.getId();
    }
}

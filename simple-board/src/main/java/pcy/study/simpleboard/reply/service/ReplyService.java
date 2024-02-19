package pcy.study.simpleboard.reply.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.simpleboard.post.service.PostService;
import pcy.study.simpleboard.reply.db.Reply;
import pcy.study.simpleboard.reply.db.ReplyRepository;
import pcy.study.simpleboard.reply.model.ReplyCreateRequest;
import pcy.study.simpleboard.reply.model.ReplyDeleteRequest;

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

    @Transactional
    public void delete(ReplyDeleteRequest replyDeleteRequest) {
        var reply = findReplyById(replyDeleteRequest.id());
        log.info("Delete Reply = {}", reply);

        matchesPassword(reply, replyDeleteRequest.password());
        replyRepository.delete(reply);
    }

    private Reply findReplyById(Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("[%d] 존재하지 않는 댓글입니다.", id)));
    }

    private void matchesPassword(Reply reply, String password) {
        if (!reply.getPassword().equals(password)) {
            throw new IllegalArgumentException("잘못된 패스워드 입니다.");
        }
    }
}

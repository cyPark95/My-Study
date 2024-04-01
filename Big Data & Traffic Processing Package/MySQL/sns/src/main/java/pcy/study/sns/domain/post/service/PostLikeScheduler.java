package pcy.study.sns.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.sns.domain.post.repository.PostJdbcRepository;

@Component
@RequiredArgsConstructor
public class PostLikeScheduler {

    private final PostJdbcRepository postJdbcRepository;

    @Scheduled(fixedDelay = 1_000)
    @Transactional
    public void synchronousPostLikeCount() {
        postJdbcRepository.PostLikeCountBulkUpdate();
    }
}

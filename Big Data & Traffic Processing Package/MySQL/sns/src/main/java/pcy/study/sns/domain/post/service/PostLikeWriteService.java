package pcy.study.sns.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.post.entity.Post;
import pcy.study.sns.domain.post.entity.PostLike;
import pcy.study.sns.domain.post.repository.PostLikeRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeWriteService {

    private final PostLikeRepository postLikeRepository;

    public Long register(Post post, MemberDto memberDto) {
        var postLike = PostLike.builder()
                .memberId(memberDto.id())
                .postId(post.getId())
                .build();
        return postLikeRepository.save(postLike).getId();
    }
}

package pcy.study.sns.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.post.dto.PostDto;
import pcy.study.sns.domain.post.entity.PostLike;
import pcy.study.sns.domain.post.repository.PostLikeRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeWriteService {

    private final PostLikeRepository postLikeRepository;

    public Long register(PostDto postDto, MemberDto memberDto) {
        var postLike = PostLike.builder()
                .memberId(memberDto.id())
                .postId(postDto.id())
                .build();
        return postLikeRepository.save(postLike).getId();
    }
}

package pcy.study.sns.domain.member.repository;

import pcy.study.sns.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(Long id);

    List<Member> findAllByIdIn(List<Long> ids);

    Member save(Member member);
}

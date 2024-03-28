package pcy.study.sns.domain.member.repository;

import org.springframework.data.repository.CrudRepository;
import pcy.study.sns.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {

    List<Member> findAllByIdIn(List<Long> ids);

    Optional<Member> findByEmail(String email);
}

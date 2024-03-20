package pcy.study.sns.domain.member.repository;

import org.springframework.data.repository.CrudRepository;
import pcy.study.sns.domain.member.entity.Member;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {

    List<Member> findAllByIdIn(List<Long> ids);
}

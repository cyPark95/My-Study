package pcy.study.sns.domain.member.repository;

import org.springframework.data.repository.CrudRepository;
import pcy.study.sns.domain.member.entity.MemberNicknameHistory;

import java.util.List;

public interface MemberNicknameHistoryRepository extends CrudRepository<MemberNicknameHistory, Long> {

    List<MemberNicknameHistory> findAllByMemberId(Long memberId);
}

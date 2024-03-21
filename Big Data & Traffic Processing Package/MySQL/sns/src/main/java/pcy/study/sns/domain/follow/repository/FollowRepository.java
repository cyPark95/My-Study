package pcy.study.sns.domain.follow.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pcy.study.sns.domain.follow.entity.Follow;

import java.util.List;

@Repository
public interface FollowRepository extends CrudRepository<Follow, Long> {

    List<Follow> findAllByFromMemberId(Long fromMemberId);

    List<Follow> findAllByToMemberId(Long toMemberId);
}

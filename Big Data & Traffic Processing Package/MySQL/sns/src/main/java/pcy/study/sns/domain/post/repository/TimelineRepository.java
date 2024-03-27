package pcy.study.sns.domain.post.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pcy.study.sns.domain.post.entity.Timeline;

import java.util.List;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {

    List<Timeline> findAllByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    List<Timeline> findAllByIdLessThanAndMemberIdOrderByIdDesc(Long id, Long memberId, Pageable pageable);
}

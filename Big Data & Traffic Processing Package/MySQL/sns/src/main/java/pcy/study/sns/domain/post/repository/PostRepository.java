package pcy.study.sns.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pcy.study.sns.domain.post.dto.DailyPostCount;
import pcy.study.sns.domain.post.dto.DailyPostCountRequest;
import pcy.study.sns.domain.post.entity.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT new pcy.study.sns.domain.post.dto.DailyPostCount(memberId, createdDate, COUNT(id)) " +
            "FROM Post " +
            "WHERE memberId = :memberId AND createdDate BETWEEN :#{#request.firstDate} AND :#{#request.lastDate} " +
            "GROUP BY createdDate, memberId")
    List<DailyPostCount> groupByCreatedDate(@Param("memberId") Long memberId, @Param("request") DailyPostCountRequest request);

    Page<Post> findAllByMemberId(Long memberId, Pageable pageable);

    List<Post> findAllByIdIn(List<Long> ids);

    List<Post> findAllByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    List<Post> findAllByMemberIdInOrderByIdDesc(List<Long> memberIds, Pageable pageable);

    List<Post> findAllByIdLessThanAndMemberIdOrderByIdDesc(Long id, Long memberId, Pageable pageable);

    List<Post> findAllByIdLessThanAndMemberIdInOrderByIdDesc(Long id, List<Long> memberIds, Pageable pageable);
}

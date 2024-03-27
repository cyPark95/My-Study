package pcy.study.sns.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pcy.study.sns.domain.post.entity.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Long countByPostId(Long postId);
}

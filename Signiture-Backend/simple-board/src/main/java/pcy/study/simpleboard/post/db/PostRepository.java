package pcy.study.simpleboard.post.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(
            value = "SELECT p " +
                    "FROM Post p " +
                    "LEFT JOIN FETCH p.replies r " +
                    "WHERE p.id = :id"
    )
    Optional<Post> findByIdFetchReply(@Param("id") Long id);
}

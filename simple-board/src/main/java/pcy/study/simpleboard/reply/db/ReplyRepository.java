package pcy.study.simpleboard.reply.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByPostIdOrderByIdDesc(Long postId);
}

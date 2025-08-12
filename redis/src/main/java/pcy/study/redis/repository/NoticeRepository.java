package pcy.study.redis.repository;

import org.springframework.stereotype.Repository;
import pcy.study.redis.domain.Notice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class NoticeRepository {

    private static Long sequence = 0L;
    private final Map<Long, Notice> notices = new ConcurrentHashMap<>();

    public Notice findById(Long id) {
        return notices.get(id);
    }

    public Long save(Notice notice) {
        Notice newNotice = new Notice(sequence, notice.getContent());
        notices.put(sequence++, newNotice);
        return newNotice.getId();
    }
}

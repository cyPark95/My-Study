package pcy.study.redis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pcy.study.redis.domain.Notice;
import pcy.study.redis.repository.NoticeRepository;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Cacheable(value = "notice",  key="#id")
    public Notice getNotice(Long id) {
        return noticeRepository.findById(id);
    }

    public Long addNotice(String content) {
        Notice notice = new Notice(content);
        return noticeRepository.save(notice);
    }
}

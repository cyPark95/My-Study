package pcy.study.redis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import pcy.study.redis.domain.Notice;
import pcy.study.redis.repository.NoticeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class NoticeServiceTest {

    private static final String CONTENT = "Notice Content";

    @Autowired
    private NoticeService noticeService;

    @MockitoSpyBean
    private NoticeRepository noticeRepository;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    void clearCache() {
        cacheManager.getCache("notice").clear();
    }

    @DisplayName("공지 등록 & 공지 조회 검증")
    @Test
    void noticeAddAndGet() {
        Notice invalidNotice = noticeService.getNotice(-1L);
        assertNull(invalidNotice);

        Long id = noticeService.addNotice(CONTENT);

        Notice notice = noticeService.getNotice(id);
        assertEquals(CONTENT, notice.getContent());
    }

    @DisplayName("최초 공지 조회 시 캐시 적용 & 캐시 이후 Repository 조회 없이 반환 검증")
    @Test
    void getCachingNotice() {
        Long id = noticeService.addNotice(CONTENT);

        Notice notice = noticeService.getNotice(id);
        Notice cacheNotice1 = noticeService.getNotice(id);
        Notice cacheNotice2 = noticeService.getNotice(id);

        assertEquals(notice.getContent(), cacheNotice1.getContent());
        assertEquals(notice.getContent(), cacheNotice2.getContent());

        verify(noticeRepository, times(1)).findById(id);
    }
}

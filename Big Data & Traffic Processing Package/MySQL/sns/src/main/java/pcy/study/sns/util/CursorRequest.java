package pcy.study.sns.util;

/**
 * 커서 기반 페이징
 * 어떤 정보를 Key로 정하는지가 중요하다.
 * - 인덱스가 있어야 한다.
 * - 중복된 키가 없어야 한다.
 */
public record CursorRequest(Long key, int size) {

    public static final Long NONE_KEY = -1L;

    public boolean hasKey() {
        return key != null;
    }

    public CursorRequest next(Long key) {
        return new CursorRequest(key, size);
    }
}

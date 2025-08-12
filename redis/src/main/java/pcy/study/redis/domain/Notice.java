package pcy.study.redis.domain;

public class Notice {

    private Long id;

    private String content;

    public Notice() {
    }

    public Notice(String content) {
        this.content = content;
    }

    public Notice(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

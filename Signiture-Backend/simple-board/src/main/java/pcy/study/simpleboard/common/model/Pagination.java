package pcy.study.simpleboard.common.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@ToString
public class Pagination <T> {

    private final List<T> data;
    private final Integer page;
    private final Integer size;
    private final Integer currentElements;
    private final Integer totalPage;
    private final Long totalElements;

    public Pagination(Page<T> page) {
        this.data = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
        this.currentElements = page.getNumberOfElements();
        this.totalPage = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}

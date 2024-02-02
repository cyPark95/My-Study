package pcy.study.springmvcframe.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Member {
    private Long id;
    private final String username;
    private final int age;

    public void setSequence(Long id) {
        this.id = id;
    }
}

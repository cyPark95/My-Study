package pcy.study.springmvcframe.app.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Member {
    private Long id;
    private final String username;
    private final String password;
    private final int age;

    public void setSequence(Long id) {
        this.id = id;
    }
}

package pcy.study.simpleboard.board.db;

import jakarta.persistence.*;
import lombok.*;
import pcy.study.simpleboard.common.db.BaseTimeEntity;
import pcy.study.simpleboard.common.db.Status;
import pcy.study.simpleboard.post.db.Post;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"posts"})
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR")
    private Status status;

    @OneToMany(mappedBy = "board")
    private final List<Post> posts = new ArrayList<>();

    @Builder
    private Board(String name) {
        this.name = name;
        this.status = Status.REGISTERED;
    }

    public void addPost(Post post) {
        posts.add(post);
    }
}

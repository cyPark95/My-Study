package pcy.study.simpleboard.post.db;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import pcy.study.simpleboard.board.db.Board;
import pcy.study.simpleboard.common.db.BaseTimeEntity;
import pcy.study.simpleboard.common.db.Status;
import pcy.study.simpleboard.reply.db.Reply;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"board", "replies"})
@Entity
@SQLRestriction("status = 'REGISTERED'")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String email;
    private String title;
    private String contents;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "post")
    private final List<Reply> replies = new ArrayList<>();

    @Builder
    private Post(
            String userName,
            String password,
            String email,
            String title,
            String contents
    ) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.title = title;
        this.contents = contents;
        this.status = Status.REGISTERED;
    }

    public void registerBoard(Board board) {
        if (Objects.nonNull(this.board)) {
            this.board.getPosts().remove(this);
        }
        this.board = board;
        board.addPost(this);
    }

    public void addReply(Reply reply) {
        this.replies.add(reply);
    }
}

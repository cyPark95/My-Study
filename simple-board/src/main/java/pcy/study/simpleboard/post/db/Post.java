package pcy.study.simpleboard.post.db;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import pcy.study.simpleboard.common.db.BaseTimeEntity;
import pcy.study.simpleboard.common.db.Status;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@SQLRestriction("status = 'REGISTERED'")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;
    private String userName;
    private String password;
    private String email;
    private String title;
    private String contents;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR")
    private Status status;

    @Builder
    private Post(
            Long boardId,
            String userName,
            String password,
            String email,
            String title,
            String contents
    ) {
        this.boardId = boardId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.title = title;
        this.contents = contents;
        this.status = Status.REGISTERED;
    }
}

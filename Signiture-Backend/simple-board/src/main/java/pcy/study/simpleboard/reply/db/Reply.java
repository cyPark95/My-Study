package pcy.study.simpleboard.reply.db;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import pcy.study.simpleboard.common.db.BaseTimeEntity;
import pcy.study.simpleboard.common.db.Status;
import pcy.study.simpleboard.post.db.Post;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"post"})
@Entity
@SQLRestriction("status = 'REGISTERED'")
public class Reply extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String title;
    private String contents;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    private Reply(
            String userName,
            String password,
            String title,
            String contents
    ) {
        this.userName = userName;
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.status = Status.REGISTERED;
    }

    public void registerPost(Post post) {
        if (Objects.nonNull(this.post)) {
            this.post.getReplies().remove(this);
        }

        this.post = post;
        post.addReply(this);
    }
}
